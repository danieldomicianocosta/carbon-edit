# JWT Token Handling for Access Control

Este módulo implementa uma solução para extrair informações do token JWT e disponibilizá-las para uso em regras de negócio relacionadas a listas de acesso de usuários.

## Visão Geral

A solução consiste em:

1. **UserContext**: Armazena informações do usuário atual durante a requisição
2. **JwtTokenFilter**: Extrai informações do token JWT e as armazena no UserContext
3. **SecurityConfig**: Configura o filtro JWT para interceptar requisições
4. **AccessControlUtil**: Utilitário para verificar acesso com base nas informações do usuário

## Como Funciona

1. Quando uma requisição chega, o `JwtTokenFilter` intercepta a requisição e extrai o token JWT do cabeçalho `Authorization`
2. O filtro decodifica o token e extrai as informações do usuário (username e role)
3. Essas informações são armazenadas no `UserContext` para uso durante a requisição
4. Após a requisição ser processada, o contexto é limpo para evitar vazamento de memória

## Uso em Serviços

Para usar as informações do usuário em seus serviços, você pode:

1. Acessar diretamente o `UserContext`:

```java
UserContext.UserInfo userInfo = UserContext.getCurrentUser();
if (userInfo != null) {
    String username = userInfo.getUsername();
    String role = userInfo.getRole();
    // Use as informações do usuário
}
```

2. Usar o `AccessControlUtil` para verificações de acesso:

```java
@Autowired
private AccessControlUtil accessControlUtil;

public void algumMetodo() {
    // Verificar se o usuário tem um papel específico
    if (accessControlUtil.hasRole("IT_ADMINISTRADOR")) {
        // Lógica para administradores
    }
    
    // Obter o nome de usuário atual
    String username = accessControlUtil.getCurrentUsername();
    
    // Verificar acesso a um recurso específico
    if (accessControlUtil.hasAccess(resourceId)) {
        // Lógica para usuários com acesso
    }
}
```

## Exemplo Prático

Veja a classe `AccessListServiceWithUserContext` para um exemplo de como usar o `UserContext` em um serviço real para implementar regras de acesso baseadas no usuário e papel extraídos do token JWT.

## Configuração

A chave secreta para validação do token JWT é configurada no arquivo `application.yml`:

```yaml
jwt:
  secret: chave_secreta_muito_longa_para_garantir_seguranca_adequada_do_jwt
```

## Formato do Token

O token JWT deve conter as seguintes claims:

- `sub`: O nome de usuário
- `role`: O papel do usuário

Exemplo de payload decodificado:
```json
{
  "sub": "root",
  "role": "IT_ADMINISTRADOR",
  "created": 1741268052164,
  "exp": 1741872852
}
```

## Considerações de Segurança

1. Em ambiente de produção, a chave secreta deve ser armazenada de forma segura
2. Considere adicionar validação de expiração e outros claims
3. Implemente um tratamento de erros mais robusto para tokens inválidos
