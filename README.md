## Gerador de certificados 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

POC para estudo de geração de arquivos PDF utilizando a biblioteca JasperReports e integração com o Spring Boot.

### API

#### POST /certificates/generate

**Request body**
```json
{
	"name": "John Doe",
	"courseDuration": 12,
	"course": "Java",
	"startDate": "2025-03-02T13:39:00Z",
	"endDate": "2025-04-02T12:32:00Z"
}
```
**Response**
![Exemplo](.github/example.png)