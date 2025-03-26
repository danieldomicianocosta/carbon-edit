# Proposal Analysis Endpoint Documentation

This document describes the new endpoints for retrieving proposals by status, which are part of the analysis package.

## Endpoints

### Get Proposals by Status ID

```
GET /api/analysis/proposals/status/{statusId}
```

Retrieves all proposals with the given status ID.

#### Path Parameters

- `statusId` (required): The ID of the status to filter by. Must be a valid status ID from the `ProposalStatus` enum.

#### Response

Returns a list of proposals with the given status, including related data such as customer, partner, business executive, and vehicle information.

#### Example

```
GET /api/analysis/proposals/status/68
```

Response:
```json
[
  {
    "id": 1,
    "proposalNumber": "PROP-001",
    "createDate": "2025-01-01T10:00:00",
    "validityDate": "2025-02-01T10:00:00",
    "finishedDate": "2025-01-15T10:00:00",
    "statusDescription": "Finalizado Com Venda",
    "customerName": "John Doe",
    "partnerName": "Partner 1",
    "businessExecutive": "Executive 1",
    "modelBrand": "Model 1",
    "serviceOrder": "SO-001"
  },
  ...
]
```

### Get Proposals by Status ID with Pagination

```
GET /api/analysis/proposals/status/{statusId}/paginated
```

Retrieves all proposals with the given status ID with pagination.

#### Path Parameters

- `statusId` (required): The ID of the status to filter by. Must be a valid status ID from the `ProposalStatus` enum.

#### Query Parameters

- `page` (optional): The page number (0-based). Default is 0.
- `size` (optional): The page size. Default is 20.
- `sort` (optional): The sort criteria. Format is `property,direction` where direction is `asc` or `desc`. Default is `createDate,desc`.

#### Response

Returns a paginated list of proposals with the given status, including related data such as customer, partner, business executive, and vehicle information.

#### Example

```
GET /api/analysis/proposals/status/68?page=0&size=10&sort=createDate,desc
```

Response:
```json
{
  "content": [
    {
      "id": 1,
      "proposalNumber": "PROP-001",
      "createDate": "2025-01-01T10:00:00",
      "validityDate": "2025-02-01T10:00:00",
      "finishedDate": "2025-01-15T10:00:00",
      "statusDescription": "Finalizado Com Venda",
      "customerName": "John Doe",
      "partnerName": "Partner 1",
      "businessExecutive": "Executive 1",
      "modelBrand": "Model 1",
      "serviceOrder": "SO-001"
    },
    ...
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 100,
  "totalPages": 10,
  "first": true,
  "last": false
}
```

### Get Proposals by Status Name

```
GET /api/analysis/proposals/status-name?statusName={statusName}
```

Retrieves all proposals with the given status name.

#### Query Parameters

- `statusName` (required): The name of the status to filter by. Must be a valid status name from the `ProposalStatus` enum (e.g., "FINALIZADO_COM_VENDA").

#### Response

Returns a list of proposals with the given status, including related data such as customer, partner, business executive, and vehicle information.

#### Example

```
GET /api/analysis/proposals/status-name?statusName=FINALIZADO_COM_VENDA
```

Response:
```json
[
  {
    "id": 1,
    "proposalNumber": "PROP-001",
    "createDate": "2025-01-01T10:00:00",
    "validityDate": "2025-02-01T10:00:00",
    "finishedDate": "2025-01-15T10:00:00",
    "statusDescription": "Finalizado Com Venda",
    "customerName": "John Doe",
    "partnerName": "Partner 1",
    "businessExecutive": "Executive 1",
    "modelBrand": "Model 1",
    "serviceOrder": "SO-001"
  },
  ...
]
```

### Get Proposals by Status Name with Pagination

```
GET /api/analysis/proposals/status-name/paginated?statusName={statusName}
```

Retrieves all proposals with the given status name with pagination.

#### Query Parameters

- `statusName` (required): The name of the status to filter by. Must be a valid status name from the `ProposalStatus` enum (e.g., "FINALIZADO_COM_VENDA").
- `page` (optional): The page number (0-based). Default is 0.
- `size` (optional): The page size. Default is 20.
- `sort` (optional): The sort criteria. Format is `property,direction` where direction is `asc` or `desc`. Default is `createDate,desc`.

#### Response

Returns a paginated list of proposals with the given status, including related data such as customer, partner, business executive, and vehicle information.

#### Example

```
GET /api/analysis/proposals/status-name/paginated?statusName=FINALIZADO_COM_VENDA&page=0&size=10&sort=createDate,desc
```

Response:
```json
{
  "content": [
    {
      "id": 1,
      "proposalNumber": "PROP-001",
      "createDate": "2025-01-01T10:00:00",
      "validityDate": "2025-02-01T10:00:00",
      "finishedDate": "2025-01-15T10:00:00",
      "statusDescription": "Finalizado Com Venda",
      "customerName": "John Doe",
      "partnerName": "Partner 1",
      "businessExecutive": "Executive 1",
      "modelBrand": "Model 1",
      "serviceOrder": "SO-001"
    },
    ...
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 100,
  "totalPages": 10,
  "first": true,
  "last": false
}
```

### Get Proposals Count by Status

```
GET /api/analysis/proposals/count-by-status
```

Retrieves the count of proposals grouped by status. This endpoint is particularly useful for creating charts and dashboards.

#### Response

Returns a list of counts for each status, including the status ID, name, description, and count.

#### Example

```
GET /api/analysis/proposals/count-by-status
```

Response:
```json
[
  {
    "statusId": 60,
    "statusName": "INITIAL",
    "statusDescription": "Início do Fluxo",
    "count": 10
  },
  {
    "statusId": 61,
    "statusName": "EM_EDICAO",
    "statusDescription": "Em Edição",
    "count": 15
  },
  {
    "statusId": 68,
    "statusName": "FINALIZADO_COM_VENDA",
    "statusDescription": "Finalizado Com Venda",
    "count": 25
  },
  ...
]
```

## Status Values

The following status values are available:

| Status Name | Status ID | Description |
|-------------|-----------|-------------|
| INITIAL | 60 | Início do Fluxo |
| EM_EDICAO | 61 | Em Edição |
| REPROVADO_COMERCIAL | 63 | Reprovado Comercial |
| APROVADO_COMERCIAL | 64 | Aprovado Comercial |
| EM_APROVACAO_COMERCIAL | 62 | Em Aprovação Comercial |
| APROVACAO_COMERCIAL | 64 | Aprovação Comercial |
| PROPOSTA_ENVIADA | 65 | Proposta Enviada |
| VALIDACAO_BACKOFFICE | 66 | Validação Backoffice |
| APROVADO_BACKOFFICE | 72 | Aprovado Backoffice |
| FINALIZADO_SEM_VENDA | 69 | Finalizado Sem Venda |
| REPROVADO_BACKOFFICE | 67 | Reprovado Backoffice |
| ENCERRADO_SEM_RETORNO | 70 | Encerrado Sem Retorno |
| FINALIZADO_COM_VENDA | 68 | Finalizado Com Venda |
| CANCELADO | 71 | Cancelado |

## Error Handling

The endpoints return the following error responses:

- `400 Bad Request`: If the status ID or name is invalid.
- `500 Internal Server Error`: If there is an error processing the request.

## Implementation Details

The endpoints are implemented using the following components:

- `ProposalAnalysisController`: The controller that exposes the endpoints.
- `ProposalAnalysisService`: The service that implements the business logic.
- `ProposalAnalysisRepository`: The repository that retrieves the data from the database.
- `ProposalsByStatusResponseDTO`: The DTO that represents the response.

The SQL queries used to retrieve the data are documented in `src/main/resources/db/analysis/proposal_by_status.sql`.
