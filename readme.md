# File Service Server

## Overview

This project is a robust Spring Boot application designed to handle secure file upload and download operations. It provides a scalable, containerized solution for file management with comprehensive deployment options.

## Features

- **Secure File Upload**: Upload files using API key authentication
- **Easy File Download**: Retrieve files by filename
- **Docker Containerization**: Consistent deployment across environments
- **Kubernetes Support**: Scalable and reliable deployment
- **Flexible Configuration**: Environment-specific settings

## Prerequisites

- **Java Development Kit (JDK) 17**
- **Gradle**
- **Docker**
- **Kubernetes** (for cluster deployment)

## Project Structure

Key components of the project:

- **`FileController.java`**: Handles HTTP requests for file operations
- **`FileServiceApplication.java`**: Spring Boot application entry point
- **`application.properties`**: Configuration management
- **`Dockerfile`**: Docker containerization script
- **`build.gradle.kts`**: Gradle build configuration
- **Kubernetes Manifests**: Deployment, Service, and Ingress configurations

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/addyklicks/file-service-server.git
cd file-service-server
```

### 2. Local Development

#### Build the Application

```bash
./gradlew clean build
```

#### Run the Application Locally

```bash
./gradlew bootRun
```

The application starts on port 8080 by default.

### 3. Docker Deployment

#### Build Docker Image

```bash
docker build -t file-service:1.0 .
```

#### Run Docker Container

```bash
docker run -p 8080:8080 --name file-service-container file-service:1.0
```

### 4. Kubernetes Deployment

#### Apply Kubernetes Manifests

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

## API Endpoints

### File Upload

**POST** `/files`
- Requires `Celonis-Auth` header with API key
- Attach file in the request body

```bash
curl -X POST -H "Celonis-Auth: your_api_key" -F file=@/path/to/file http://localhost:8080/files
```

### File Download

**GET** `/files/{filename}`

```bash
curl -O http://localhost:8080/files/your_filename
```

## Configuration

Configure the application using `application.properties`:

- `celonis.api-key`: API key for authentication
- `file.storage.path`: Directory for file storage

## Multi-Realm Deployment Challenges and Solutions

### Context

At Celonis, we operate multiple instances (realms) of our Execution Management System (EMS) globally, spanning different cloud providers. This introduces unique challenges in maintaining and deploying services efficiently.

### Challenges and Strategies

#### 1. Environment-Specific Configurations

**Problem:** Each realm requires unique configurations for:
- Database hosts
- Connectivity settings
- API endpoints

**Solution:**
- Implement Infrastructure as Code (IaC)
- Use centralized configuration management
- Leverage Kubernetes ConfigMaps

#### 2. Consistency Across Realms

**Problem:** Maintaining consistent application versions and configurations

**Solution:**
- Robust CI/CD pipeline
- Kubernetes namespaces
- Standardized deployment processes

#### 3. Scaling and Monitoring

**Problem:** Managing application performance across multiple cloud providers

**Solution:**
- Multi-cloud monitoring solutions
- Centralized dashboards
- Auto-scaling policies

## Recommended Improvements

### Security Enhancements
- Kubernetes Secrets management
- OAuth2/JWT authentication
- HTTPS support with Ingress TLS

### Scalability
- Horizontal Pod Autoscaling (HPA)
- Readiness and Liveness Probes
- Zero-Downtime Deployments

### Monitoring
- Centralized logging (ELK Stack)
- Prometheus and Grafana integration

### Data Persistence
- Persistent Volume Claims (PVCs)
- Cloud storage backup strategies

## Performance Considerations

- Lightweight JRE runtime
- Efficient file handling
- Containerized for consistent performance
- Kubernetes-ready for horizontal scaling

## Potential Future Enhancements

1. Advanced authentication mechanisms
2. Comprehensive logging and monitoring
3. Multi-cloud deployment strategies
4. Enhanced error handling
5. Database metadata integration

## Contributing

Contributions are welcome! Please:
- Fork the repository
- Create a feature branch
- Submit a pull request

## License

MIT License - See [LICENSE](LICENSE) for details

## Contact

For inquiries, please open an issue on the GitHub repository.

---

*Designed for scalable, secure, and efficient file management*
