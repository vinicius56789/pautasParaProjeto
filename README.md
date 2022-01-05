# API - Votação :man: :woman: :e-mail:



Projeto feito baseado em um teste prático de uma seleção a qual meu tio participou e me envio o enunciado para que eu pudesse fazer também. O projeto consiste em uma api para sistema de votação onde um usuário consegue votar se aprova ou não a pauta colocada em questão.



### FUNCIONAMENTO



O projeto possui entidades que se relacionam e a partir de um pacote é possível persistir dados via JPA. De forma geral, é possível cadastrar Pautas, Associados e computar votos que esses associados fizeram. Existe ainda um prazo limite para votação e qualquer voto fora desse horário é descartado.



### PACOTES



1. model;
2. dto;
3. repository;
4. controller;



#### model

> Pacote de classes que dão corpo as tabelas do banco de dados.



#### dto

> Pacote para pegar alguns atributos das classes do pacote modelo e transcreve-los em outro tipo de objeto para proteger e restringir o acesso a classe principal. Aqui também ficam os métodos que realizam a transferência.



#### repository

> Pacote de interface JpaRepository, cada entidade possui a sua e é usada para acesso ao banco de dados.



#### controller

> Pacote de criação dos HTTP da API. Esse pacote combina o uso de todos os outros para gerenciar a API e suas funcionalidades.