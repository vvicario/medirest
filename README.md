# rest example

# Requiere Java 1.8 y maven

# Installation
Run `mvn clean install`.

# Deploying
Run `App.java` from your favorite IDE. To verify your deployment,
test this endpoint

GET to http://localhost:8080/api/users.  You should get back some data.

# Setting Up Swagger

1) Add the following dependency to our pom.xml

     <dependency>
           <groupId>io.swagger</groupId>
                <artifactId>swagger-jaxrs</artifactId>
                <version>1.5.0</version>
     </dependency>

2) To your pom.xml, you need to add the ability to download and and then copy the Swagger-UI static resources to your project's resources folder.
   That way when you package and run your server, it will be able to display the Swagger UI at http:://<host>:8080/.
   Similar to here, we will add the following lines to our pom.xml, in the <plugins></plugins> portion:
   Make sure you have this /src/main/resources/swagger folder

            <plugin>
                <groupId>com.googlecode.maven-download-plugin</groupId>
                <artifactId>download-maven-plugin</artifactId>
                <version>1.2.1</version>
                <executions>
                    <execution>
                        <id>swagger-ui</id>
                        <goals>
                            <goal>wget</goal>
                        </goals>
                        <configuration>
                            <url>https://github.com/swagger-api/swagger-ui/archive/v${swagger.ui.version}.tar.gz</url>
                            <unpack>true</unpack>
                            <outputDirectory>${project.build.directory}</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-resources-plugin</artifactId>
                <version>2.6</version>
                <executions>
                    <execution>
                        <id>copy-resources-to-src</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${basedir}/src/main/resources/swagger</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>${project.build.directory}/swagger-ui-${swagger.ui.version}/dist</directory>
                                    <filtering>true</filtering>
                                    <excludes>
                                        <exclude>index.html</exclude>
                                    </excludes>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                    <execution>
                        <id>copy-resources-to-target</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${basedir}/target/classes/assets</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>${project.build.directory}/swagger-ui-${swagger.ui.version}/dist</directory>
                                    <filtering>true</filtering>
                                    <excludes>
                                        <exclude>index.html</exclude>
                                    </excludes>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>


 3) Add the Swagger package scanning servlet. Now, to configure and initialize Swagger.
You'll now manually create and add the Swagger servlet. Update App.java with the following code:

        String resourceBasePath = Main.class.getResource(
                "/swagger").toExternalForm();
        context.setResourceBase(resourceBasePath);
        context.setWelcomeFiles(new String[] { "index.html" });
        ServletHolder swaggerUiServlet = new ServletHolder(new DefaultServlet());
        swaggerUiServlet.setInitOrder(2);
        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");

4) Run App.java, open http://localhost:8080/ and you will see swagger ui, update api-docs.json as you need
