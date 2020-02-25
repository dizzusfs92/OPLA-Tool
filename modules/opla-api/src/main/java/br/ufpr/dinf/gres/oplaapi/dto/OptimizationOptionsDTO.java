package br.ufpr.dinf.gres.oplaapi.dto;

import jmetal4.experiments.OptimizationAlgorithm;
import jmetal4.experiments.FeatureMutationOperators;
import jmetal4.experiments.Metrics;

import java.util.Arrays;
import java.util.List;

public class OptimizationOptionsDTO {
    public List<OptimizationAlgorithm> algorithms = Arrays.asList(OptimizationAlgorithm.values());
    public List<Metrics> objectiveFunction = Arrays.asList(Metrics.values());
    public List<FeatureMutationOperators> featureMutationOperators = Arrays.asList(FeatureMutationOperators.values());
}
