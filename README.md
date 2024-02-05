Импользуется:

| aspectj               |  version  |  1.9.7   |
| allure                |  version  |  2.25.0  |
| junit                 |  version  |  4.13.2  |
| rest-assured          |  version  |  5.3.0   |
| gson                  |  version  |  2.10.1  |
| maven-surefire-plugin |  version  |  2.10.0  |
| javafaker             |  version  |  1.0.2   |
| lombok                |  version  |  1.18.28 |
| selenium 				|version 	|  4.15.0  |


Для запуска всех тестов:
`mvn clean test`

Для запуска тестов в Яндекс Браузере
`mvn test -Dbrowser=yandex`

Для формирования отчета Allure
`mvn allure:serve`