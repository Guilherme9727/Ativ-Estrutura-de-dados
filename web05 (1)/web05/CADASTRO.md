# Componente de cadastro com Bootstrap

Implementação em `src/app/cadastro/cadastro.ts`, `cadastro.html` e `cadastro.css`.
A rota `/cadastro` e o Bootstrap 5.3.8 via CDN já estavam configurados no projeto.

## Executar

1. Extraia o arquivo e abra um terminal dentro da pasta `web05`.
2. Execute `npm ci` para instalar as dependências do projeto.
3. Execute `npm start`.
4. Abra `http://localhost:4200/cadastro`.

Se preferir aplicar ao projeto que você já tem, copie apenas os três arquivos
`cadastro.ts`, `cadastro.html` e `cadastro.css` para `src/app/cadastro/`.
Não é necessário instalar Bootstrap novamente. O Bootstrap via CDN requer internet.

## O que foi implementado

- Layout responsivo usando card, grid, botões e alertas do Bootstrap.
- Nome e e-mail obrigatórios; telefone opcional com DDD.
- Senha com 8 a 128 caracteres e confirmação de senha.
- Validação com FormsModule, ngModel e NgForm.
- Erros exibidos ao sair do campo ou tentar enviar.
- Botão Limpar que reinicia os valores e o estado do formulário.
- Labels, autocomplete e associação das mensagens de erro aos campos.

O botão Cadastrar apenas valida o formulário e mostra uma mensagem demonstrativa.
Não há API, persistência ou autenticação implementada. Nenhum dado ou senha é salvo
no navegador. Para um cadastro real, integrar o método cadastrar a uma API e validar
os dados também no servidor.

## Verificação

Código TypeScript e templates verificados com o compilador Angular (ngc), sem erros.
A compilação completa via ng build foi bloqueada pelas dependências nativas do
Windows presentes no anexo, incompatíveis com o Linux usado na verificação.
Não foi realizada verificação visual em navegador.

O pacote contém o código-fonte e os arquivos de configuração do projeto.
Dependências (node_modules), cache (.angular) e metadados Git não foram incluídos.
