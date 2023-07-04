package com.parthibanrajasekaran;//package com.parthibanrajasekaran;
//
//import au.com.dius.pact.provider.junit5.HttpTestTarget;
//import au.com.dius.pact.provider.junit5.PactVerificationContext;
//import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
//import au.com.dius.pact.provider.junitsupport.Provider;
//import au.com.dius.pact.provider.junitsupport.State;
//import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
//import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.TestTemplate;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Provider("CoursesCatalogue")
////@PactFolder("src/main/java/pacts") // By default, it looks in src/main/java
//@PactBroker(url = "https://parthibanrajasekaran.pactflow.io", authentication = @PactBrokerAuth(token = "kcV49WSgJWSC7vgjlNlWIQ"), enablePendingPacts = "false")
//public class PactCoursesTests {
//
//    @LocalServerPort
//    // fetches the value of SpringBootTest.WebEnvironment.RANDOM_PORT generated at run time from @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    public int port;
//
//    @TestTemplate // Number of tests depend on the number of interactions in the pact file
//    @ExtendWith({ PactVerificationInvocationContextProvider.class, SpringExtension.class })
//    public void pactVerificationTest(PactVerificationContext context) {
//        context.verifyInteraction();
//    }
//
//    @BeforeEach // For test runner to know where the pact server shall be hosted at run time
//    public void setup(PactVerificationContext context) {
//        context.setTarget(new HttpTestTarget("localhost", port));
//    }
//
////    @State(value = "courses exist", action = StateChangeAction.SETUP)
////    public void coursesExistSetup() {
////
////    }
////
////    @State(value = "courses exist", action = StateChangeAction.TEARDOWN)
////    public void courseExistTearDown() {
////
////    }
////
////    @State(value = "course appium exists", action = StateChangeAction.SETUP)
////    public void courseAppiumExistSetup() {
////
////    }
////
////    @State(value = "course appium exists", action = StateChangeAction.TEARDOWN)
////    public void courseAppiumExistTearDown() {
////
////    }
////
////    @State(value = "course spring boot does not exists", action = StateChangeAction.SETUP)
////    public void courseSpringbootUnavailableSetup() {
////
////    }
////
////    @State(value = "course spring boot does not exists", action = StateChangeAction.TEARDOWN)
////    public void courseSpringbootUnavailableTearDown() {
////
////    }
//
//    @State("course appium exists")
//    public void courseAppiumExists() {
//        // set up the state of the provider, e.g. create or update a database record
//    }
//
//    @State("course spring boot does not exists")
//    public void courseSpringBootDoesNotExists() {
//        // set up the state of the provider, e.g. delete a record from the database
//    }
//
//    @State("courses exist")
//    public void courseExist() {
//        // set up the state of the provider, e.g. create or update a database record
//    }
//}
