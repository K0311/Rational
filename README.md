# Rational JUnit exercise

Java fraction implementation and JUnit 4 tests for the ITCS386 exercise.

## Files

- `src/main/java/Rational.java`: implementation.
- `src/test/java/RationalTest.java`: 40 JUnit tests.
- `build.xml`: Ant compilation, tests, reports, JAR and run targets.
- `pom.xml`: Maven build with JUnit 4.13.
- `build.gradle`: Gradle build with Java 8 bytecode and local JUnit/Hamcrest JARs.
- `lib`: the repository's JUnit and Hamcrest dependencies.

Use JDK 8 for the worksheet. Keep the implementation and tests in separate files, with no package declaration. Set the Project SDK, module SDK and JUnit runtime consistently. A Gradle version that requires a newer JVM must run on its supported JVM; the Java compilation target remains 8.

## Input space partitioning

The tests cover default construction; reducible fractions; positive, negative and zero numerators; positive, negative and zero denominators; all four arithmetic operations; division by zero; equal and unequal fractions; null and unrelated objects; less/equal/greater comparisons; formatted strings; and large integer boundaries.

The default fraction is 0/1. Fractions are reduced and their denominators are positive. Zero denominators throw Rational.Illegal; division by zero throws ArithmeticException. compareTo rejects null and non-Rational objects. BigInteger intermediates prevent silent arithmetic overflow; results outside the stored long range throw ArithmeticException without partially changing the original fraction.

The tests were committed before the implementation. Against the original stub, the final suite ran 40 tests with 36 failures. Against the completed implementation, JUnit ran 40 tests with no failures. This is a check against the repository baseline; it does not claim that every case was developed through a separate TDD cycle.

## IntelliJ JUnit

Open pom.xml as a Maven project, allow dependency import, and run the RationalTest class (all tests). Expect 40 tests to pass. Maven and Gradle use JUnit 4, not JUnit 5.

## Ant

Enable the Ant plugin/tool window, add build.xml, and run compile and test. With Ant installed:

```text
ant clean test
ant jar
ant run
```

Reports: `report/TEST-RationalTest.txt` and `report/TEST-RationalTest.xml`.
The text and XML reports contain test names, counts, elapsed times and failure/error details.
Dependencies: test -> compile-test -> compile; run -> jar -> compile.
Clean is explicit so rebuilding a JAR does not delete the test reports.

## Maven

In IntelliJ, add pom.xml as a Maven project and run Lifecycle -> compile, then install. With Maven installed:

```text
mvn clean install
```

Reports: `target/surefire-reports`. First use requires Maven Central access.

## Gradle

Import build.gradle as a Gradle project in IntelliJ and run verification -> test. With Gradle installed:

```text
gradle clean test
```

If your local project already has a compatible Gradle wrapper, use `gradlew.bat clean test` on Windows instead. The starter repository does not include a wrapper.

HTML report: `build/reports/tests/test/index.html`.
XML results: `build/test-results/test`.
Gradle uses the bundled JARs, so the test libraries do not need to be downloaded.

## Validation performed

- Compiled implementation and test sources with Java 8 language/bytecode settings using Eclipse compiler on a Java 17 runtime.
- JUnit 4.13: 40 tests passed.
- Ant: 40 tests, zero failures/errors; both report formats generated.
- Ant JAR and run: passed; prints "This is Rational class."
- Maven install was attempted but dependency retrieval failed because this environment could not resolve Maven Central; Maven success is not claimed.
- Gradle execution was not verified in this environment. Run Maven and Gradle locally before submission.

## Submission

After successful local checks, ensure your changes are committed and pushed. Download the updated repository using GitHub Code -> Download ZIP. The worksheet specifies the filename `ITCS473 [YOUR ID] JUnit.zip`; use your actual student ID and any newer instructor instruction.
