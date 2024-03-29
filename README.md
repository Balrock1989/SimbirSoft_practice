# SimbirSoft_practice
Техническое задание
Общие сведения
● Разработать приложение, через которое будет осуществляться работа со
складом новогодних игрушек.
Технологии
● Java 8+
● Git
● Maven
● PostgreSQL
● Elasticsearch
● JUnit
● SpringMVC
Функции приложения
● Управление складом сети магазинов (добавление, поиск, обновление и
удаление товара)
● Вывод имеющихся товаров со стоимостью по товарным группам.
● Покупка товаров в магазине.
● Вывод информации о выручке магазинов от проданных товаров.
Требования к реализации
● Не использовать средства автоматической генерации кода (JOOQ, Lombok и
т.п.)
● Приложение должно предоставлять Swagger API по работе со складом
● БД должна быть реплицирована, версионирование - Liquibase
● Покрытие тестами (Sonarlint) не менее 60%
● Все интерфейсы сервисов должны поставляться с javadoc
● Каждый этап должен быть покрыт тестами, javadoc, Swagger
Требования к поставке
● Репозиторий. Проект загружается в private репозиторий на github. В
репозитории должны отсутствовать специфические для интегрированной среды
разработки файлы, а также файлы собранного приложения. Необходимо
придерживаться модели ветвления Gitflow. Каждую фиксацию изменений
сопровождать понятным комментарием.
● Сборка проекта. Сборка проекта осуществляется через Maven. Описание
процессов сборки и работы с приложением находится в README файле.
Этапы
● 1 часть Каркас приложения. Сущности, таблицы БД (без репликации но уже с
Liquibase), CRUD, Swagger
● 2 часть Функционал. Товар добавляется по накладным, убирается по покупке
из магазина, либо списывается. Добавляются товарные группы. Покупка
представляет заказ с позициями. Списание представляет комбинированную
операцию в 2 этапа. Сначала создается список на списание, далее этот список
должен быть подтвержден отдельно.
● 3 часть Отчеты. По проданным товарам по магазинам. По списанным за
период времени. По выручке магазинов. По среднему чеку магазинов.
Репликация БД.
● 4 часть Поиск. Реализовать поиск по неточному совпадению по наименованию
товара. Поиск по вхождению в товарную группу.
