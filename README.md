This is the companion website to the paper **Combinatorial Robustness Testing with Negative Test Cases** by Konrad Fögen and Horst Lichter. It is submitted to the 19th IEEE International Conference on *Software Quality, Reliability, and Security* [(QRS-2019)](https://qrs19.techconf.org/) and currently in review.

- *src/main/java/de/rwth/swc/qrs2019* contains source code which implements the overall evaluation framework
- *src/test/java/de/rwth/swc/qrs2019* contains the scenarions named *ScenarioX_Y.java* and corresponding test methods to generate test inputs and to execute the scenarios named *Test__ScenarioX_Y_Z.java*. 
- *evaluation/* contains the results of test input generation and execution of all scenarios
- *evaluation/ Fault_Detection_Effectiveness.xlsx* summarizes the results.

To obtain more detailed reports, the test methods must be executed either individually in the IDE or via `mvn clean test`. Please keep in mind that executing all scenarios might take some time. 

Reference implementations of the algorithms presented in the paper are also published. An example is provided below. See https://coffee4j.github.io/ for more information.

```java
class GreetingsTest {

    private static InputParameterModel model() {
        return inputParameterModel("example-model")
                .strength(2)
                .parameters(
                        parameter("Title").values("Mr", "Mrs"),
                        parameter("FirstName").values("John", "Jane"),
                        parameter("LastName").values("Doo", "Foo")
                ).errorConstraints(
                        constrain("Title", "FirstName").by((String title, String firstName)
                                -> { if(title.equals("Mr")) { return !firstName.equals("Jane"); } else return true; } ),
                        constrain("Title", "FirstName").by((String title, String firstName)
                                -> { if(title.equals("Mrs")) { return !firstName.equals("John"); } else return true; })
      ).build();
    }

    @CombinatorialTest
    @ModelFromMethod("model")
    @Generator( { Ipog.class, IpogNeg.class } )
    void testGreetings(String title, String firstName, String lastName) {
        System.out.println("Hello " + title + " " + firstName + " " + lastName);
    }
}

```

