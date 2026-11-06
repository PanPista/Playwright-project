FROM maven:3.9.9-eclipse-temurin-17-noble

WORKDIR /tests

# Cache Maven dependencies: copy pom.xml first
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Install only Chromium (the only browser used by the tests) with system deps.
# Uses the Java CLI so the browser build matches the Playwright version in pom.xml.
RUN mvn -B -q exec:java -Dexec.mainClass=com.microsoft.playwright.CLI \
      -Dexec.args="install --with-deps chromium" && \
    rm -rf /var/lib/apt/lists/*

COPY . .

CMD ["mvn", "verify", "-Dexec.skip=true"]
