# Documentação das Rotas Camel para Propostas

Este documento descreve as rotas Camel implementadas para o gerenciamento de propostas completas, incluindo instruções para teste usando a coleção Postman fornecida.

## Visão Geral

A implementação utiliza o Apache Camel para orquestrar o fluxo de dados entre os diferentes componentes do sistema. As rotas Camel implementam padrões de integração empresarial (EIPs) para melhorar a modularidade, extensibilidade e performance do BFF para leitura e atualização de propostas.

## Rotas Implementadas

### 1. Rota de Busca de Proposta Completa

- **Endpoint**: `GET /api/proposals/{id}/complete`
- **Rota Direta**: `direct:findCompleteProposal`
- **Padrões Implementados**: Splitter, Aggregator, Content Enricher
- **Descrição**: Busca uma proposta completa pelo ID, incluindo todos os dados relacionados (detalhes, veículos, itens, comissões, documentos e acompanhamentos).

### 2. Rota de Atualização de Proposta Completa

- **Endpoint**: `PUT /api/proposals/{id}/complete`
- **Rota Direta**: `direct:updateCompleteProposal`
- **Padrões Implementados**: Content-Based Router, Transaction Manager
- **Descrição**: Atualiza uma proposta completa pelo ID, permitindo atualizações parciais de diferentes componentes da proposta.

## Componentes Principais

### Processadores

- **ProposalProcessor**: Processa atualizações da proposta básica
- **ProposalDetailProcessor**: Processa atualizações dos detalhes da proposta
- **ProposalDetailVehicleProcessor**: Processa atualizações dos veículos da proposta
- **ProposalUpdateProcessor**: Implementa o roteamento baseado no conteúdo

### Agregadores e Enriquecedores

- **ProposalResultAggregator**: Combina os resultados das sub-consultas
- **ProposalResultEnricher**: Adiciona informações adicionais à proposta completa

### Validadores

- **ProposalValidator**: Valida a existência da proposta e os dados de entrada

## Testando as Rotas com Postman

Uma coleção Postman foi criada para facilitar o teste das rotas Camel. A coleção está disponível em `src/main/resources/static/CamelProposalRoutes.postman_collection.json`.

### Configuração

1. Importe a coleção no Postman
2. Configure a variável `proposalId` com o ID de uma proposta existente no sistema

### Requisições Disponíveis

1. **Buscar Proposta Completa**: Busca uma proposta completa pelo ID
2. **Atualizar Proposta Completa**: Atualiza todos os dados de uma proposta
3. **Atualizar Apenas Proposta Básica**: Demonstra a capacidade de atualização parcial
4. **Atualizar Apenas Detalhes da Proposta**: Demonstra a capacidade de atualização parcial
5. **Atualizar Apenas Veículos da Proposta**: Demonstra a capacidade de atualização parcial

### Exemplos de Uso

#### Buscar Proposta Completa

```
GET http://localhost:8080/api/proposals/1/complete
```

#### Atualizar Apenas o Status da Proposta

```json
{
    "proposal": {
        "id": 1,
        "statusClaId": 2,
        "statusDate": "2025-03-26T00:00:00",
        "creationDate": "2025-03-26T00:00:00",
        "customerId": 1,
        "sellerId": 1,
        "dealershipId": 1
    }
}
```

## Benefícios da Implementação

1. **Paralelismo**: As consultas são divididas em sub-consultas que podem ser executadas em paralelo, melhorando a performance.
2. **Modularidade**: Cada componente tem uma responsabilidade única e bem definida, facilitando a manutenção.
3. **Extensibilidade**: Novos componentes podem ser adicionados facilmente sem modificar os existentes.
4. **Desacoplamento**: A lógica de negócio está separada da lógica de roteamento e acesso a dados.
5. **Reutilização**: Os componentes podem ser reutilizados em diferentes contextos.
6. **Atualizações Parciais**: Permite atualizar apenas partes específicas da proposta, sem precisar enviar o objeto completo.

## Diagrama de Fluxo

```mermaid
flowchart TD
    API[API Controller] --> Router[Camel Router]
    
    subgraph Leitura
        Router --> FindRoute[Find Proposal Route]
        FindRoute --> Splitter[Splitter]
        Splitter --> |Proposal| ProposalRepo[Proposal Repository]
        Splitter --> |Detail| DetailRepo[Detail Repository]
        Splitter --> |Vehicles| VehicleRepo[Vehicle Repository]
        Splitter --> |Documents| DocRepo[Document Repository]
        Splitter --> |Commissions| CommRepo[Commission Repository]
        Splitter --> |FUPs| FupRepo[FUP Repository]
        
        ProposalRepo --> Aggregator[Aggregator]
        DetailRepo --> Aggregator
        VehicleRepo --> Aggregator
        DocRepo --> Aggregator
        CommRepo --> Aggregator
        FupRepo --> Aggregator
        
        Aggregator --> Enricher[Content Enricher]
        Enricher --> |Status Rules| StatusService[Status Service]
        Enricher --> ResponseBuilder[Response Builder]
    end
    
    subgraph Atualização
        Router --> UpdateRoute[Update Proposal Route]
        UpdateRoute --> Validator[Validator]
        Validator --> CBR[Content-Based Router]
        
        CBR --> |Proposal| ProposalProcessor[Proposal Processor]
        CBR --> |Detail| DetailProcessor[Detail Processor]
        CBR --> |Vehicles| VehicleProcessor[Vehicle Processor]
        CBR --> |Documents| DocProcessor[Document Processor]
        CBR --> |Commissions| CommProcessor[Commission Processor]
        CBR --> |FUPs| FupProcessor[FUP Processor]
        
        ProposalProcessor --> TransactionManager[Transaction Manager]
        DetailProcessor --> TransactionManager
        VehicleProcessor --> TransactionManager
        DocProcessor --> TransactionManager
        CommProcessor --> TransactionManager
        FupProcessor --> TransactionManager
        
        TransactionManager --> FindRoute
    end
