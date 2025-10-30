## Ссылка на сайт тестирования
https://automationexercise.com/

## Ссылки на API спецификацию
https://automationexercise.com/api_list

## Ссылки на GUI спецификацию
https://automationexercise.com/test_cases

## Команды для запуска тестов
**Все тесты**
./gradlew test
./gradlew runAllTestsWithReport - с генерацией отчета Allure

**Только UI тесты**
./gradlew uiTest
./gradlew runUiTestsWithReport - с генерацией отчета Allure

**Только API тесты**
./gradlew apiTest
./gradlew runApiTestsWithReport - с генерацией отчета Allure

**Специфичные API тесты**
**Только позитивные API тесты**
./gradlew apiPositiveTest
./gradlew runApiPositiveTestsWithReport - с генерацией отчета Allure

**Только негативные API тесты**
./gradlew apiNegativeTest
./gradlew runApiNegativeTestsWithReport - с генерацией отчета Allure

## Структура проекта 
src/test/java/ui/ - UI тесты (помечены тегом @Tag("ui"))
src/test/java/api/ - API тесты (помечены тегом @Tag("api"))
src/test/java/api/tests/ApiPositiveTests - позитивные API тесты
src/test/java/api/tests/ApiNegativeTests - негативные API тесты

## Настройки
Браузер: Chrome
Размер окна: 1920x1080
Headless режим: выключен
Отчеты: Allure в папке build/allure-results

## Особенности запуска
- Режим инкогнито включен для чистого состояния браузера
- Каждый тест запускается без кеша, истории и автозаполнения
- Гарантирует изолированное тестовое окружение
- Тестовые пользователи генерируются при помощи faker

## Требования 
Java 11+
Gradle
Chrome браузер