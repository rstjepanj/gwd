package hr.tvz.gwd.web;

import org.encog.neural.networks.BasicNetwork;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import hr.tvz.gwd.model.DesignProperties;

public class GeneticAlgorithm {
	
	public static final int NUMBER_OF_EVOLUTIONS = 2;
	public static final double ZELJENA_OCJENA = 5;
	public static final int POPULATION_SIZE = 15;

	public static DesignProperties generateDesign(BasicNetwork network) {
		Configuration.reset();
		Configuration conf = new DefaultConfiguration();
		FitnessFunction fitnessFunction = new DesignFitnessFunction(network);
		
		try {
			conf.setFitnessFunction(fitnessFunction);
			conf.addGeneticOperator(new CrossoverOperator(conf));
			conf.setPreservFittestIndividual(true);
			conf.setKeepPopulationSizeConstant(true);
			
			Gene[] sampleGenes = getSampleGenes(conf);
			
			Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
			conf.setSampleChromosome(sampleChromosome);
			conf.setPopulationSize(POPULATION_SIZE);
			Genotype population = Genotype.randomInitialGenotype(conf);
			
			for (int i = 0; i < NUMBER_OF_EVOLUTIONS; i++) {		
				population.evolve();
				if (population.getFittestChromosome().getFitnessValue() * 5 > ZELJENA_OCJENA)
					break;
			}
			
			return chromosomeToDesignProperties(population.getFittestChromosome());
			
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
		return new DesignProperties();
	}

	private static Gene[] getSampleGenes(Configuration conf) throws InvalidConfigurationException {
		Gene[] sampleGenes = {
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.colorMin, DesignProperties.colorMax),
				new IntegerGene(conf, DesignProperties.textSizeMin, DesignProperties.textSizeMax),
				new IntegerGene(conf, DesignProperties.textSizeMin, DesignProperties.textSizeMax),
				new IntegerGene(conf, DesignProperties.textSizeMin, DesignProperties.textSizeMax),
				new IntegerGene(conf, DesignProperties.textSizeMin, DesignProperties.textSizeMax),
				new IntegerGene(conf, DesignProperties.textSizeMin, DesignProperties.textSizeMax),
				new IntegerGene(conf, DesignProperties.fontMin, DesignProperties.fontMax),
				new IntegerGene(conf, DesignProperties.fontMin, DesignProperties.fontMax)
		};
		return sampleGenes;
	}

	public static DesignProperties chromosomeToDesignProperties(IChromosome chromosome) {
		DesignProperties design = new DesignProperties();
		design.setNavbarBackColorR((Integer)chromosome.getGene(0).getAllele());
		design.setNavbarBackColorG((Integer)chromosome.getGene(1).getAllele());
		design.setNavbarBackColorB((Integer)chromosome.getGene(2).getAllele());
		design.setNavbarTextColorR((Integer)chromosome.getGene(3).getAllele());
		design.setNavbarTextColorG((Integer)chromosome.getGene(4).getAllele());
		design.setNavbarTextColorB((Integer)chromosome.getGene(5).getAllele());
		design.setBodyBackColorR((Integer)chromosome.getGene(6).getAllele());
		design.setBodyBackColorG((Integer)chromosome.getGene(7).getAllele());
		design.setBodyBackColorB((Integer)chromosome.getGene(8).getAllele());
		design.setBodyTextColorR((Integer)chromosome.getGene(9).getAllele());
		design.setBodyTextColorG((Integer)chromosome.getGene(10).getAllele());
		design.setBodyTextColorB((Integer)chromosome.getGene(11).getAllele());
		design.setLinkColorR((Integer)chromosome.getGene(12).getAllele());
		design.setLinkColorG((Integer)chromosome.getGene(13).getAllele());
		design.setLinkColorB((Integer)chromosome.getGene(14).getAllele());
		design.setWellBackColorR((Integer)chromosome.getGene(15).getAllele());
		design.setWellBackColorG((Integer)chromosome.getGene(16).getAllele());
		design.setWellBackColorB((Integer)chromosome.getGene(17).getAllele());
		design.setBodyTextSize((Integer)chromosome.getGene(18).getAllele());
		design.setLeadTextSize((Integer)chromosome.getGene(19).getAllele());
		design.setH1TextSize((Integer)chromosome.getGene(20).getAllele());
		design.setH2TextSize((Integer)chromosome.getGene(21).getAllele());
		design.setH4TextSize((Integer)chromosome.getGene(22).getAllele());
		design.setBodyFont((Integer)chromosome.getGene(23).getAllele());
		design.sethPlusLeadFont((Integer)chromosome.getGene(24).getAllele());
		return design;
	}
}
