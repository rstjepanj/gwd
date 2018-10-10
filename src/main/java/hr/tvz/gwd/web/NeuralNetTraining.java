package hr.tvz.gwd.web;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import hr.tvz.gwd.db.DesignPropertiesRepository;
import hr.tvz.gwd.db.UserRepository;
import hr.tvz.gwd.model.DesignProperties;
import hr.tvz.gwd.model.User;

@Component
public class NeuralNetTraining {
	
	public static final int NOVE_OCJENE_PRIJE_TRENIRANJA = 100;
	public static final int ULAZNI_PARAMETARI = 25;
	public static final int SEKUNDE_TRENIRANJA = 5;
	public static final double TRAINING_ERROR = 0.10;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DesignPropertiesRepository designPropertiesRepository;

	@Async
	public void checkNeuralNet(User user) {
		int savedCount = designPropertiesRepository.countByUser(user);
		if (savedCount != 0 && savedCount % NOVE_OCJENE_PRIJE_TRENIRANJA == 0) {
			BasicNetwork network = new BasicNetwork();
			network.addLayer(new BasicLayer(null, true, ULAZNI_PARAMETARI));
			network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 10));
			network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
			network.getStructure().finalizeStructure();
			network.reset();

			List<DesignProperties> data = designPropertiesRepository.findAllByUser(user);
			double[][] input = new double[data.size()][ULAZNI_PARAMETARI];
			double[][] ideal = new double[data.size()][1];
			
			for (int i = 0; i < data.size(); i++) {
				input[i] = data.get(i).getNormalizedInputArray();
				ideal[i][0] = data.get(i).getNormalizedOcjena();
			}

			MLDataSet trainingSet = new BasicMLDataSet(input, ideal);
			
			final Backpropagation train = new Backpropagation(network, trainingSet);
			
			long start = System.currentTimeMillis();
			System.out.println("Početak treniranja");
			do {
				train.iteration();
			} while (System.currentTimeMillis() < start + SEKUNDE_TRENIRANJA * 1000 && train.getError() > TRAINING_ERROR);
			System.out.println("Kraj treniranja, error: " + train.getError());
			train.finishTraining();
			
			try {
				user.setNetwork(new SerialBlob(SerializationUtils.serialize(network)));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			userRepository.saveAndFlush(user);
		}
	}

}
