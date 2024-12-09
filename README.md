Модуль "Product sets" (Комплекты для товара (Up-Sell)) + тема Юни2(MVRu) v4.18.3.
Тест можно запустить через файл TestNG.xml

Рекомендуется **запускать** проект через файл TestNG.xml. Но можно каждый тест запускать по отдельности.
В файле data.properties (папка src -- test -- resources) вписать браузер, в котором будет происходить проверка.

**Условия для работоспособности авто-тестов:**
1) Следить за актуальными версиями браузеров и их драйверов (папка src -- test -- resources);
2) Следить за актуальностью библиотек (файл pom.xml)
3) В класс Constants добавить актуальную ссылку, на которой будут запускаться авто-тесты.


Сайт для chromedriver: https://getwebdriver.com/

Сайт для msedgedriver: https://developer.microsoft.com/ru-ru/microsoft-edge/tools/webdriver/?form=MA13LH

Сайт для geckodriver: https://github.com/mozilla/geckodriver/releases