package br.unb.cic.etl;

import br.uff.ic.gems.phoenix.PhoenixDiffCalculator;
import br.uff.ic.gems.phoenix.SettingsHelper;
import br.unb.cic.etl.io.Util;
import br.unb.cic.etl.model.ScriptETL;
import br.unb.cic.etl.model.SimilarityGraph;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class MainProgram {

    public static void main(String args[]) {

        assert (args.length > 0);

        MainProgram p = new MainProgram();

        SimilarityGraph g = new SimilarityGraph();
        SettingsHelper.setSimilarityThreshold(0.7f);

        try {
            List<File> etls = null;

            if(args.length == 1) {
                etls = Util.findFiles(args[0]);
            }
            else {
                etls = new ArrayList<>();
                etls.add(new File(args[0]));
                etls.add(new File(args[1]));
            }

            Map<String, ScriptETL> scripts = new HashMap<>();
            int id = 1;
            for (File f1 : etls) {
                scripts.put(f1.getAbsolutePath(), new ScriptETL(id++, f1.getAbsolutePath()));
            }

            PrintWriter writer = new PrintWriter(new FileWriter("file.csv", true));
            int comparison = 0;

            PhoenixDiffCalculator diffCalculator = null;

            for (File f1 : etls) {
                for (File f2 : etls) {
                    if (f1.getAbsolutePath().equals(f2.getAbsolutePath())) {
                        continue;
                    }

                    comparison++;

                    diffCalculator = new PhoenixDiffCalculator(f1.getAbsolutePath(), f2.getAbsolutePath());

                    Document doc = diffCalculator.executeComparison();

                    double diff = Double.parseDouble(doc.getDocumentElement().getAttribute("diff:similarity"));
                    writer.println(f1.getAbsolutePath() + "," + f2.getAbsolutePath() + "," + diff);
                    //g.addEdge(scripts.get(f1.getAbsolutePath()), scripts.get(f2.getAbsolutePath()), value);
                }
            }
            writer.close();
//            ClusteringAlgorithm.Clustering<ScriptETL> c = g.findClusters();
//            System.out.println(c.getClusters().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
