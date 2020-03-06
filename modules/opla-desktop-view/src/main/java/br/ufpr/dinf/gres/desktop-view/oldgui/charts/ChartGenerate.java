/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.dinf.gres.domain.oldgui.charts;

import br.ufpr.dinf.gres.domain.oldgui.configuration.GuiFile;
import br.ufpr.dinf.gres.domain.oldgui.configuration.UserHome;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.xy.XYDataItem;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author elf
 */
public class ChartGenerate {

    /**
     * @param functions             - functions[0] = x Axis, functions[1] y Axis
     * @param mapExperimentIdToFile - map contendo o id do experimento e o path
     *                              para o arquivo contendo os valores das funcoes objetivos
     */
    public static void generate(String[] functions, HashMap<String, String> experimentToAlgorithmUsed, int[] columns, String outputDir, String expId) throws IOException {

        String name = "Solutions in the Search Space (" + br.ufpr.dinf.gres.domain.db.Database.getPlaUsedToExperimentId(expId) + ")";
        ChartGeneratorScatter g = new ChartGeneratorScatter(name, functions[1], functions[0]);

        for (Map.Entry<String, String> entry : experimentToAlgorithmUsed.entrySet()) {

            List<List<Double>> content = br.ufpr.dinf.gres.domain.db.Database.getAllObjectivesForNonDominatedSolutions(entry.getKey(), columns);
            HashMap<String, List<XYDataItem>> algo = new HashMap<>();
            List<XYDataItem> one = new ArrayList<>();

            for (List<Double> list : content) {
                one.add(new XYDataItem(list.get(1), list.get(0)));
            }

            algo.put(entry.getValue() + " - " + entry.getKey(), one);
            g.setDataSet(algo);
        }

        ChartPanel chartPanel = g.plot();

        JFrame frame = new JFrame(name);
        frame.add(chartPanel);
        if (GuiFile.getInstance().getSaveChartsAsPng().equals("y")) {

            new File(UserHome.getOplaUserHome() + "charts/").mkdirs();
            ChartUtilities.saveChartAsPNG(new File(UserHome.getOplaUserHome() + "charts/" + name.replaceAll(" ", "_") + ".png"), chartPanel.getChart(), 800, 600);
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}