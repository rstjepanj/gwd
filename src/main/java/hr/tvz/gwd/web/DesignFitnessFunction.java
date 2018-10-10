package hr.tvz.gwd.web;

import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import hr.tvz.gwd.model.DesignProperties;

public class DesignFitnessFunction extends FitnessFunction {

	private static final long serialVersionUID = 1L;
	
	private BasicNetwork network;
	
	public DesignFitnessFunction(BasicNetwork network) {
		this.network = network;
	}

	@Override
	protected double evaluate(IChromosome a_subject) {
		DesignProperties design = GeneticAlgorithm.chromosomeToDesignProperties(a_subject);
		return network.compute(new BasicMLData(design.getNormalizedInputArray())).getData(0);
	}

}
