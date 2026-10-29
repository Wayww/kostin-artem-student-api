# kostin-artem-student-api

Student: Artem Kostin

Minimal Spring Boot Student CRUD API with PostgreSQL.

## Gradle

```bash
./gradlew clean build
./gradlew test
```

## Docker Compose

Set the database variables before starting the containers. Do not commit these values.

```bash
export POSTGRES_DB=studentdb
export POSTGRES_USER=studentuser
export POSTGRES_PASSWORD='choose-a-password'
docker compose up --build -d
```

Stop the containers without deleting the PostgreSQL volume:

```bash
docker compose down
```

## CRUD endpoints

```text
POST   /api/students
GET    /api/students
GET    /api/students/{id}
PUT    /api/students/{id}
DELETE /api/students/{id}
```

Examples:

Successful student creation returns HTTP 201 and a `Location` header for the created student.

```bash
curl -X POST http://localhost:8080/api/students -H 'Content-Type: application/json' -d '{"name":"Artem Kostin"}'
curl http://localhost:8080/api/students
curl http://localhost:8080/api/students/1
curl -X PUT http://localhost:8080/api/students/1 -H 'Content-Type: application/json' -d '{"name":"Artem Kostin"}'
curl -X DELETE http://localhost:8080/api/students/1
```

## Linux demonstration

System report and permissions:

```bash
chmod 744 scripts/Kostin_Artem_system_report.sh
ls -l scripts/Kostin_Artem_system_report.sh
./scripts/Kostin_Artem_system_report.sh
cp README.md backups/Kostin_Artem_README.md
cat backups/Kostin_Artem_README.md
```

Process and log analysis:

```bash
ps aux
ps aux | grep '[j]ava'
tail -n 5 logs/Kostin_Artem_system_report.log
grep 'Kostin' logs/Kostin_Artem_system_report.log
grep 'Kostin' logs/Kostin_Artem_system_report.log > Kostin_Artem_log_analysis.txt
```

## Git demonstration

Create the repository and initial version:

```bash
git init
git branch -M main
git add .
git commit -m "Initial commit: Artem Kostin Student API"
git remote add origin <GitHub repository URL for kostin-artem-student-api>
git push -u origin main
git tag v1.0-kostin-artem
git push origin v1.0-kostin-artem
```

Use `feature/kostin-artem-crud` for one CRUD improvement and its README update. Make at least two meaningful commits, then merge it into `main`:

```bash
git switch -c feature/kostin-artem-crud
git add .
git commit -m "Improve student CRUD operation"
git add README.md
git commit -m "Update CRUD documentation"
git switch main
git merge feature/kostin-artem-crud
git push origin main
```

For the merge-conflict demonstration, create `feature/kostin-artem-documentation` and change this sentence differently in that branch and in `main`: Student API documentation is maintained in README.md.

## Container demonstration

```bash
docker compose ps
docker compose logs app
docker inspect kostin-artem-student-api-app-1
docker compose exec app sh
docker compose stop app
docker compose restart app
docker compose build app
docker compose up -d
curl http://localhost:8080/api/students
```

Create a student before `docker compose restart`, then repeat the final `curl` command to demonstrate that PostgreSQL records remain in the named volume.

## Jenkins demonstration

Create a Pipeline job named `Kostin_Artem_Student_API` from the GitHub repository. The agent must provide JDK 17, Docker, and access to the Docker daemon. Configure the GitHub webhook or SCM polling manually. The pipeline performs Checkout, Build, Test, Docker Build, and Verify only; it does not deploy the application.

For troubleshooting, run a successful pipeline, create a test branch, intentionally break a test, show the failed pipeline, fix the test, and show the successful pipeline.
