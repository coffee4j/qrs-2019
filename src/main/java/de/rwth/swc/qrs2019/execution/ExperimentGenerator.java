package de.rwth.swc.qrs2019.execution;

import de.rwth.swc.coffee4j.engine.CombinatorialTestModel;
import de.rwth.swc.coffee4j.engine.generator.ipog.Ipog;
import de.rwth.swc.coffee4j.engine.generator.negative.IpogNeg;
import de.rwth.swc.coffee4j.model.InputParameterModel;
import de.rwth.swc.coffee4j.model.converter.IndexBasedModelConverter;
import java.util.List;
import java.util.stream.Collectors;

public class ExperimentGenerator {

    public static List<int[]> generatePositiveTestSuite(InputParameterModel inputParameterModel) {
        final IndexBasedModelConverter converter = new IndexBasedModelConverter(inputParameterModel);
        final CombinatorialTestModel model = converter.getConvertedModel();

        final List<int[]> testInputs = new Ipog()
                .generate(model, new StandardOutputReporter()).iterator().next().get()
                .getTestInputs();

        return testInputs;
    }

    public static List<int[]> generateNegativeTestSuite(InputParameterModel inputParameterModel) {
        final IndexBasedModelConverter converter = new IndexBasedModelConverter(inputParameterModel);
        final CombinatorialTestModel model = converter.getConvertedModel();

        final List<int[]> testInputs = new IpogNeg()
                .generate(model, new StandardOutputReporter())
                .stream()
                .map(testInputGroupSupplier -> testInputGroupSupplier.get().getTestInputs())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return testInputs;
    }
}
