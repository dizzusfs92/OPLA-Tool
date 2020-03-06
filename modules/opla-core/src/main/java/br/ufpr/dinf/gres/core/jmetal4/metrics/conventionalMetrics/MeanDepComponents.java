package br.ufpr.dinf.gres.core.jmetal4.metrics.conventionalMetrics;

import br.ufpr.dinf.gres.architecture.representation.Architecture;
import br.ufpr.dinf.gres.architecture.representation.Element;
import br.ufpr.dinf.gres.architecture.representation.Interface;
import br.ufpr.dinf.gres.architecture.representation.Package;

import java.util.ArrayList;
import java.util.List;

public class MeanDepComponents {

    private double results;

    public MeanDepComponents(Architecture architecture) {

        this.results = 0;
        List<Element> depComponents = new ArrayList<>();
        int totalComponents = architecture.getAllPackages().size();
        int totalDependencies = 0;

        for (Package component : architecture.getAllPackages()) {
            for (Interface itf : component.getRequiredInterfaces()) {
                for (Element c : itf.getImplementors()) {
                    if (!depComponents.contains(c))
                        depComponents.add(c);
                }
            }
            totalDependencies += depComponents.size();

        }
        if (totalComponents != 0) {
            this.results = totalDependencies / totalComponents;
        }
    }

    public double getResults() {
        return results;
    }

}