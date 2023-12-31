# Java Developer Test 
## Задание
__Создание системы ранжирования покерных рук (наборов карт игрока).__  
Используемые средства: Java  
Референсные материалы: [Правила Техасского холдема](https://en.wikipedia.org/wiki/Texas_hold_%27em )

## Решение
Предлагается оценивать каждую комбинацию карт по шкале от 0 до 9 и сравнивать их между собой.
Также будет создан список "kickers", содержащий карты в определенном порядке для разрешения спорных ситуаций, когда оценки комбинаций совпадают.

Для этой задачи найдем следующие параметры:

1. Количество уникальных карт (от 1 до 5 штук).
2. Максимальное количество одинаковых карт.
   
Кроме того, потребуются булевы переменные для следующих условий:
3. Последовательность карт.
4. Совпадение мастей всех карт (флеш).
5. Наличие туза (для определения самой сильной комбинации).

Для определения критериев ранжирования создадим таблицу и выявим минимальный набор информации для каждого случая.

![Таблица с данными](https://github.com/BorbotDen/Poker/blob/master/image.png)

Будем последовательно проходить от верхнего к нижнему критерию, проверяя данные, отмеченные зеленым. Каждое последующее условие исключает необходимость повторной проверки предыдущих.
