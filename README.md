## Инструменты:
OpenJDK 17

maven/gradle

Junit5

## Дано:
Все звонки, совершенные абонентом сотового оператора, фиксируются в CDR файлы, которые собираются на коммутаторах. Когда абонент находится в роуминге за процесс сбора его данных отвечает обслуживающая сеть абонента. Для стандартизации данных между разными операторами международная ассоциация GSMA ввела стандарт BCE. Согласно ему, данные с CDR должны агрегировать в единый отчет UDR, который впоследствии передается оператору, обслуживающему абонента в домашней сети. На основе этого отчета, домашний оператор выставляет абоненту счет.

## В рамках задания, CDR будут содержать записи следующего вида:

 тип вызова (01 - исходящие, 02 - входящие);
 
 номер абонента;
 
 дата и время начала звонка (Unix time);
 
дата и время окончания звонка;

 разделитель данных – запятая;
 
 разделитель записей – перенос строки;
 
данные обязательно формируются в хронологическом порядке;

В рамках задания CDR может быть обычным txt;

## Вот пример фрагмента CDR:

02,79876543221, 1709798657, 1709799601

01,79996667755, 1709899870, 1709905806
 

## UDR будет агрегировать данные по абонентам и суммировать длительность вызовов разного типа.
Пример UDR объекта для абонента 79876543221


{

    "msisdn": "79876543221",
    
    "incomingCall": {
    
        "totalTime": "02:12:13"
        
    },
    
    "outcomingCall": {
    
        "totalTime": "00:02:50"
        
    }
    
}
 
## Задача 1:

Напишите сервис, эмулирующий работу коммутатора, т.е. генерирующий CDR файлы.

Условия:
1.       1 CDR = 1 месяц. Тарифицируемый период в рамках задания - 1 год;
2.       Данные в CDR идут не по порядку, т.е. записи по одному абоненту могут быть в разных частях файла;
3.       Количество и длительность звонков определяется случайным образом;
4.       Установленный список абонентов (не менее 10) хранится в локальной БД (h2);
5.       После генерации CDR, данные о транзакциях пользователя помещаются в соседнюю таблицу этой БД.
## Задача 2:

Данные полученные от CDR сервиса передать в сервис генерации UDR. Агрегировать данные по каждому абоненту в отчет.

          	Условия:
           
1.       Данные можно брать только из CDR файла. БД с описанием транзакций – тестовая, и доступа к ней, в рамках задания нет.
2.       Сгенерированные объекты отчета разместить в /reports.
 
Шаблон имени: номер_месяц.json (79876543221_1.json);

3.       Класс генератора должен содержать методы:
  
a.        generateReport() – сохраняет все отчеты и выводит в консоль таблицу со всеми абонентами и итоговым временем звонков по всему тарифицируемому периоду каждого абонента;

b.       generateReport(msisdn) – сохраняет все отчеты и выводит в консоль таблицу по одному абоненту и его итоговому времени звонков в каждом месяце;

c.       generateReport(msisdn, month) – сохраняет отчет и выводит в консоль таблицу по одному абоненту и его итоговому времени звонков в указанном месяце.
## Общие условия:
1.       Конечное решение должно быть описано в одном модуле (монолит);
2.       Допустимо использовать фреймворк Spring и его модули, но приложение НЕ должно запускаться на локальном веб-сервере;
3.       По умолчанию должен срабатывать метод generateReport();
4.       В директории /tests должно быть не мене 3 unit тестов;
5.       К ключевым классам добавить javadoc описание;
6.       Конечное решение размещаете на репозитории в github в виде проекта и jar файла с зависимостями;
7.       В репозитории разместить md описание задания и вашего решения.
## Критерии:
Знание Java core актуальной версии;

Умение работать с инструментарием экосистемы Java;

Умение работать с БД;

Грамотно составленная архитектура решения;

Умение описывать выбранный подход;

«Чистота» кода;

Оптимальность при работе с ресурсами;
 
Работоспособность решения;
 
Гибкость и расширяемость.

## Глоссарий:
CDR – Call Data Record – формат файла, содержащего в себе информацию о действиях, совершенных абонентом за тарифицируемый период.
 
BCE – Billing and Charging Evolution – стандарт обмена роуминговыми данными.

UDR - Usage Data Report - Отчет об использовании данных;

msisdn  - Mobile Subscriber Integrated Services Digital Number - номер мобильного абонента цифровой сети.

### tele_commutator

В проекте использованы:

Java 17,

Maven,

H2 Database,

Log4j2,

Lombok,

Mockito,

JUnit 5

## Запуск и Работа:

Основная логика работы с файлами представлена в классах сервиса - CdrGeneratorImpl и UdrGeneratorImpl.

Запуск приложения осуществляется в классе StartApplication, в пакете - in

В бд H2 создаются две таблицы, subscribers и records, на основе данных из subscribers формируется CDR - файл, данными из которого заполняется таблица records.

Из CDR - файла формируется UDR - отчеты в формате json в директории report.

Логика работы приложения покрывает все заявленные требования.

В методе StartApplication из трех методов generateReport(), generateReport(msisdn) и  generateReport(msisdn, month), сразу запускается  по - умолчанию generateReport()

Данные для CDR - файла формируются рандомно, поэтому методы generateReport() с атрибутами выполняются после получения всез необходимых данных.

В директории out лежит jar - файл
