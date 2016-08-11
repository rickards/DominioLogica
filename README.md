# DominioLogica
Resolvedor de Expressão 

Expressão: Representa os elementos da árvore em forma de operadores e expreções lógicas. (Composite)
	- AND : contém uma lista de expressão
	- OR : contém uma lista de expressão
	- NOT : apenas contém um elemento expressão
	- Implica : contém 2 elementos expressão
	- BiIMplica : contém 2 elementos expressão

Motor: Construir a arvore em forma de objetos do java. (AND, NOT, OR, Biimplica, Implica, Propositon)
	- Quando encontra um parênteses, a string dentro do parênteses é usada recursivamente para construir aquela sub arvore.
	- Primeiro é adicionado em uma lista os operadores vazios para depois adicionar os elementos vizinhos.
	- A lista irá diminuir até que fique com apenas um elemento representando toda a expreção.

Resolvedor: Paulo Henrique Editar (leis, implementar apenas o apply)
	- as expressões vai ser passadas pelas leis
	- retorna uma tupla com nome e nova expressão