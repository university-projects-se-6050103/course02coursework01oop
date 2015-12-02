## Requirements
#### Бізнес-процеси договірного відділу

###### Основні завдання, які вирішує підрозділ:
  * складання договорів із зовнішніми організаціями і специфікації (список матеріалів, що постачаються) до договору
  * відстеження виконання та зривів поставок
  * консолідований аналіз специфікацій

###### Опис предметної області

| Номер договору | Організація, з якою  укладений договір | Дата завершення договору |
|----------------|----------------------------------------|--------------------------|
| 2431           | ТехМаш                                 | 30.11.2015               |
> *договір являє собою два документи: сам договір, що має приблизну структуру*

| Матеріал            | Кількість | Дата поставки |
|---------------------|-----------|---------------|
| Бензин              | 250 т     | 01.10.2015    |
| Електропровід   | 1500 м    | 01.11.2015    |
> *і специфікація до договору, що має приблизну структуру*

Інженер, повинен на основі цих документів:
* скласти консолідований план поставок матеріалів на своє підприємство
* відзначати, що виконано з постачання, що зірвано
* формувати всілякі звіти.

## Own additional requirement
Implement some [design patterns](http://www.wikiwand.com/en/Software_design_pattern)
