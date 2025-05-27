# Code Prompt: Migrate Java Spring Boot Application to Java 21

I need assistance migrating my Maven-based Java Spring Boot application to Java 21, ensuring all legacy features are updated to leverage the latest Java 21 features. Please follow these steps to analyze and migrate my project:

1. **Project Analysis**:
   - Analyze the current state of my Spring Boot project, including:
     - Current Java version (check `java.version` in `pom.xml` or Gradle configuration).
     - Spring Boot version (check `spring-boot-starter-parent` version or dependencies).
     - Key dependencies (e.g., Spring Framework, Hibernate, Spring Security, Jackson, etc.).
     - Build tools (Maven/Gradle) and their configurations.
     - Use of deprecated APIs, Java EE (javax.*) packages, or legacy features (e.g., `sun.misc.Unsafe`, old threading models).
     - Configuration files (`application.properties` or `application.yml`) for renamed or removed properties.
     - Any third-party libraries that may not support Java 21 or Spring Boot 3.x.
   - Identify compatibility issues with Java 21 and Spring Boot 3.2/3.3 (e.g., javax.* to jakarta.* namespace changes, Hibernate 6.1, Liquibase 4.17.x).
   - Use tools like `jdeprscan` to detect deprecated APIs and `jdeps` to identify reliance on internal JDK APIs.
   - Provide a report summarizing findings, including potential migration challenges (e.g., Spring Security configuration changes, bootstrap.properties issues).

2. **Dependency and Build Configuration Updates**:
   - Update the project to use Java 21:
     - In `pom.xml`, set `<java.version>21</java.version>` and update the Maven Compiler Plugin to version 3.8.1 or higher with `<source>21</source>` and `<target>21</target>`.
   - Upgrade to Spring Boot 3.2 or 3.3:
     - Update `<parent>` to use `spring-boot-starter-parent` version 3.2.3 or 3.3.x.
     - Add `spring-boot-properties-migrator` as a runtime dependency to identify and migrate deprecated properties.
   - Replace Java EE (javax.*) dependencies with Jakarta EE (jakarta.*) equivalents (e.g., `jakarta.servlet:jakarta.servlet-api` instead of `javax.servlet:javax.servlet-api`).
   - Update key dependencies to compatible versions (e.g., Hibernate 6.1, Liquibase 4.17.x, Spring Security 6.x).
   - If using Spring Cloud, ensure compatibility with the new `spring.config.import` mechanism and replace bootstrap.properties with `application.properties` if needed.
   - Remove or update libraries that rely on internal JDK APIs (e.g., `sun.misc.Unsafe`) or deprecated modules (e.g., JAXB, JAX-WS).

3. **Code Modernization**:
   - Replace legacy Java features with Java 21 equivalents:
     - Use **Records** instead of traditional POJO classes for immutable data carriers.
     - Replace traditional threading with **Virtual Threads** (Project Loom) for thread-per-request programming where applicable (e.g., in Spring WebFlux or REST controllers).
     - Use **Pattern Matching for instanceof** and **Switch Expressions** to simplify code logic in Spring components.
     - Replace deprecated `StringBuffer` or `StringBuilder` concatenations with **Text Blocks** for multiline strings (e.g., SQL queries or JSON configurations).
     - Update any use of `SequenceInputStream` or similar with **Sequenced Collections** (e.g., `SequencedSet`, `SequencedMap`).
   - Refactor Spring Security configurations to align with Spring Security 6.x, as the migration from Spring Security 5.x to 6.x involves significant changes (e.g., new `SecurityFilterChain` API).
   - Update data access layers to use Spring Data JPA with Hibernate 6.1, ensuring compatibility with Java 21.
   - Replace deprecated Spring Boot 2.x properties or configurations (e.g., `spring.jpa.hibernate.ddl-auto` updates).
   - If applicable, explore GraalVM Native Image support for faster startup and reduced memory usage, using Spring Boot’s native image tools.

4. **Testing and Validation**:
   - Update testing dependencies (e.g., `spring-boot-starter-test`) to the latest version compatible with Spring Boot 3.2/3.3.
   - Ensure all tests pass after migration, addressing any issues with Mockito, JUnit, or other testing frameworks.
   - Perform performance testing to evaluate Java 21’s garbage collection improvements (e.g., G1, ZGC, Shenandoah) and configure the optimal garbage collector for the application.
   - Test Spring Security configurations thoroughly, as they are a common source of migration issues.

5. **Migration Tools**:
   - Use **OpenRewrite** (Maven plugin: `org.openrewrite.maven:rewrite-maven-plugin`) with recipes like `org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0`, `org.openrewrite.java.migrate.UpgradeToJava21` to automate dependency and code updates.
   - Leverage IntelliJ IDEA’s migration support for Spring Boot and Java version upgrades.
   - Run `mvn rewrite:run` to apply OpenRewrite recipes sequentially for Spring Boot 3.0, 3.1, 3.2, and 3.3.

6. **Best Practices**:
   - Follow an incremental migration approach: first upgrade to Spring Boot 2.7 (Java 11), then to Spring Boot 3.0 (Java 17), and finally to Spring Boot 3.2/3.3 (Java 21).
   - Use feature flagging or parallel runs to minimize downtime during deployment.
   - Document all changes, especially for Spring Security and database migrations.
   - Monitor application performance post-migration using Spring Boot Actuator, Grafana, or Prometheus.

7. **Deliverables**:
   - Provide an updated `pom.xml` file reflecting all dependency and plugin changes.
   - Include a sample refactored Java class demonstrating the use of Java 21 features (e.g., Records, Virtual Threads, or Pattern Matching).
   - Provide updated `application.properties` or `application.yml` with migrated properties.
   - Include a brief migration report summarizing changes, challenges, and recommendations for leveraging Java 21 features (e.g., Virtual Threads, GraalVM).

**Instructions for Agent**:
- Begin by analyzing the project’s `pom.xml`, source code, and configuration files.
- Apply changes incrementally, testing after each step (e.g., Java 11 → Java 17 → Java 21; Spring Boot 2.7 → 3.0 → 3.2/3.3).
- Use the provided artifacts as templates but adapt them to the specific project structure and requirements.
- Report any issues (e.g., incompatible libraries, Spring Security migration challenges) and suggest solutions.
- Provide a final migration report summarizing changes and recommendations for leveraging Java 21 features like Virtual Threads or GraalVM.

**Sources**:
- Spring Boot 3.0 Migration Guide: https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide
- Baeldung Spring Boot 3 Migration: https://www.baeldung.com/spring-boot-3-migration
- OpenRewrite Documentation: https://docs.openrewrite.org/