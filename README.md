# OpenWeatherMap

Este aplicativo foi desenvolvido para a entrevista da In loco media.

<p align="center">
  <img src="https://www.dropbox.com/s/pcw42u7t7ymtjtp/tela%20inicial.png?raw=true" width="250"/>
  <img src="https://www.dropbox.com/s/peut59nqbs1splu/tela%20com%20pino.jpg?raw=true" width="250"/>
  <img src="https://www.dropbox.com/s/bept9t5olhgeeif/lista%20de%20cidades%20mais%20proximas.jpg?raw=true" width="250"/>
  <img src="https://www.dropbox.com/s/q6gpc7cu4j5nwz6/informa%C3%A7%C3%B5es%20na%20tela.jpg?raw=true" width="250"/>
  <img src="https://www.dropbox.com/s/ixh11oii6caqonz/busca%20por%20nome.jpg?raw=true" width="250"/>
</p>

# O que foi pedido?

Foi pedido que fosse desenvolvido um app que exibe as informações meteorológicas de cidades ao redor do mundo.

Inicialmente seria mostrado um mapa onde o usuário iria inserir um pino e iria clicar no botão buscar. Feito isso o app deveria
requisitar um JSON na API do Open Weather Map que nos retornaria informações do clima das 15 cidades mais próximas de acordo com a lat/lon do 
ponto onde colocamos o pino. Com o JSON em mãos deveríamos apresentar em uma tela uma lista com os nomes das 15 cidades para que o usuário pudesse 
escolher qual cidade ele gostaria de obter as informações. Uma vez escolhida a cidade seria exibido na tela o nome da cidade, a temperatura máxima e mínima além de uma descrição do tempo.

Como desafio extra foi pedido que fosse criado uma funcionalidade extra ao projeto dentro do contexto do aplicativo e para isso foi implementando uma opção
que permite ao usuário de escolher a localização pelo nome e código do país (esse segundo sendo opcional). Para isso também foi utilizado a  API do Open Weather Map.

# Conclusão

Desenvolver esse aplicativo foi uma boa experiência, por que tive a oportunidade de aprender um pouco sobre a plataforma Android que até então eu nunca tinha programado. Durante a realização do app, outras 
funcionalidades também foram pensadas como ao invés do usuário colocar o pino no mapa, a busca ser feita pela localização do GPS ou dar a opção do usuário escolher em qual unidade de medida queria a temperatura, mas que devido
ao tempo não foi possível implementar.
