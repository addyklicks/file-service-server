# File Service Server

This project is a Spring Boot application designed to handle file upload and download operations. It provides RESTful endpoints for clients to upload files to the server and retrieve them as needed.

## Features

- **File Upload**: Clients can upload files to the server using a specified authentication header.
- **File Download**: Clients can download previously uploaded files by specifying the filename in the request URL.
- **Authentication**: The server uses a custom header (`Celonis-Auth`) to authenticate upload requests.

## Prerequisites

- **Java Development Kit (JDK) 17**: Ensure that JDK 17 is installed on your system.
- **Gradle**: This project uses Gradle as the build tool.
- **Docker**: Docker is used to containerize the application.

## Project Structure

The project consists of the following key components:

- **`FileController.java`**: Handles HTTP requests for file operations.
- **`FileServiceApplication.java`**: The main entry point of the Spring Boot application.
- **`application.properties`**: Configuration file for setting application-specific properties.
- **`Dockerfile`**: Script to build the Docker image for the application.
- **`build.gradle.kts`**: Gradle build script written in Kotlin DSL.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/addyklicks/file-service-server.git
cd file-service-server
```

### 2. Build the Application

Use Gradle to build the application:

```bash
./gradlew clean build
```

This command will compile the code, run tests, and package the application into a JAR file located in the `build/libs` directory.

### 3. Run the Application

You can run the application locally using Gradle:

```bash
./gradlew bootRun
```

By default, the application will start on port 8080.

### 4. Build and Run with Docker

To containerize the application using Docker:

- **Build the Docker Image**:

  ```bash
  docker build -t file-service:1.0 .
  ```

- **Run the Docker Container**:

  ```bash
  docker run -p 8080:8080 --name file-service-container file-service:1.0
  ```

Ensure that port 8080 is available on your host machine. If it's in use, you can map the container's port to a different host port by modifying the `-p` flag (e.g., `-p 9090:8080`).

## Usage

### Uploading a File

To upload a file, send a POST request to `/files` with the file attached and the `Celonis-Auth` header set to the appropriate API key:

```bash
curl -X POST -H "Celonis-Auth: your_api_key" -F file=@/path/to/your/file http://localhost:8080/files
```

### Downloading a File

To download a file, send a GET request to `/files/{filename}`:

```bash
curl -O http://localhost:8080/files/your_filename
```

Replace `your_filename` with the name of the file you want to download.

## Configuration

The application can be configured using the `application.properties` file or environment variables. Key configurations include:

- **`celonis.api-key`**: The API key required for authenticating file upload requests.
- **`file.storage.path`**: The directory path where uploaded files will be stored.

These properties can be set in the `application.properties` file or provided as environment variables when running the application.

## Kubernetes Deployment

To deploy the application on a Kubernetes cluster:

1. **Build and Push Docker Image**:

   Ensure the Docker image is accessible to your Kubernetes cluster, typically by pushing it to a container registry.

2. **Apply Kubernetes Manifests**:

   Use the provided manifests in the `k8s` directory to deploy the application:

   ```bash
   kubectl apply -f k8s/deployment.yaml
   kubectl apply -f k8s/service.yaml
   ```

These manifests define the deployment and service configurations for the application.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## Contact

For any inquiries or issues, please open an issue on the GitHub repository.




## Multi-Realm Deployment Challenges and Solutions
**Context**
At Celonis, we operate multiple instances (realms) of our Execution Management System (EMS) globally, spanning different cloud providers. This introduces unique challenges in maintaining and deploying our services efficiently.
Challenges in Multi-Realm Deployment
1. Environment-Specific Configurations
Problem: Each realm requires unique configurations for:

Database hosts
Connectivity settings
API endpoints

**Solution:**

Implement Infrastructure as Code (IaC) using Terraform or Pulumi
Use centralized configuration management systems like Consul or HashiCorp Vault
Leverage Kubernetes ConfigMaps for environment-specific configurations

2. Consistency Across Realms
Problem: Maintaining consistent application versions and configurations across different environments
Solution:

Implement a robust CI/CD pipeline using:

Jenkins
GitHub Actions
Azure DevOps


Utilize Kubernetes namespaces or separate clusters for each realm
Standardize build and deployment processes

3. Scaling and Monitoring Complexity
Problem: Managing application performance and scaling across multiple cloud providers
Solution:

Adopt multi-cloud monitoring solutions:

Datadog
New Relic
Prometheus


Implement centralized dashboards
Create auto-scaling policies based on workload patterns

Deployment Efficiency Strategy
1. Centralized CI/CD Pipeline

Create a unified pipeline with modular stages
Use environment-specific branches or parameters
Customize deployments for each realm

2. Advanced Configuration Management

Store configurations in a secure, centralized repository
Dynamically inject configurations during deployment
Use tools like Helm or Kustomize
Implement secure secret management

3. Standardized Observability

Set up a unified observability stack

Grafana with Prometheus


Create per-realm dashboards and alerts
Centralize log aggregation for improved troubleshooting

Recommended Improvements for Production Readiness
Security Enhancements

Implement Kubernetes Secrets for sensitive data
Add OAuth2 or JWT authentication
Configure HTTPS support with Ingress TLS termination

Scalability and Reliability

Use Horizontal Pod Autoscaling (HPA)
Add Readiness and Liveness Probes
Implement Zero-Downtime Deployment strategy

Monitoring and Logging

Integrate centralized logging (ELK Stack)
Set up metrics and alerting with Prometheus and Grafana

Data Persistence

Utilize Persistent Volume Claims (PVCs)
Implement robust backup strategies to cloud storage

Conclusion
By addressing these challenges with a comprehensive, standardized approach, we can ensure:

Consistent deployments
Enhanced security
Improved observability
Efficient multi-realm management
