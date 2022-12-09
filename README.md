# CardDelivery
[![Build status](https://ci.appveyor.com/api/projects/status/irw5uq5i05vxr3nh/branch/main?svg=true)](https://ci.appveyor.com/project/DmitrySavostyanov/carddelivery/branch/main)

# Домашнее задание к занятию «2.2. Selenide»

В качестве результата пришлите ссылку на ваш GitHub-проект в личном кабинете студента на сайте [netology.ru](https://netology.ru).

Все задачи этого занятия нужно делать в одном репозитории.

**Важно**: если у вас что-то не получилось, то оформляйте issue [по установленным правилам](../report-requirements.md).

**Важно**: не делайте ДЗ всех занятий в одном репозитории. Иначе вам потом придётся достаточно сложно подключать системы Continuous integration.

## Как сдавать задачи

1. Инициализируйте на своём компьютере пустой Git-репозиторий.
1. Добавьте в него готовый файл [.gitignore](../.gitignore).
1. Добавьте в этот же каталог код ваших автотестов.
1. Сделайте необходимые коммиты.
1. Добавьте в каталог `artifacts` целевой сервис (`app-card-delivery.jar` — см. раздел «Настройка CI»).
1. Создайте публичный репозиторий на GitHub и свяжите свой локальный репозиторий с удалённым.
1. Сделайте пуш — удостоверьтесь, что ваш код появился на GitHub.
1. Удостоверьтесь, что на AppVeyor сборка зелёная.
1. Поставьте бейджик сборки вашего проекта в файл README.md.
1. Ссылку на ваш проект отправьте в личном кабинете на сайте [netology.ru](https://netology.ru).
1. Задачи, отмеченные как необязательные, можно не сдавать, это не повлияет на получение зачёта.

## Настройка CI
    
Настройка CI осуществляется аналогично предыдущему заданию, за исключением того, что файл целевого сервиса теперь называется `app-card-delivery.jar`.

## Задача №1: заказ доставки карты

Вам необходимо автоматизировать тестирование формы заказа доставки карты:

![](pic/order.png)

Требования к содержимому полей:
1. Город — [один из административных центров субъектов РФ](https://ru.wikipedia.org/wiki/%D0%90%D0%B4%D0%BC%D0%B8%D0%BD%D0%B8%D1%81%D1%82%D1%80%D0%B0%D1%82%D0%B8%D0%B2%D0%BD%D1%8B%D0%B5_%D1%86%D0%B5%D0%BD%D1%82%D1%80%D1%8B_%D1%81%D1%83%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%BE%D0%B2_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D0%B9%D1%81%D0%BA%D0%BE%D0%B9_%D0%A4%D0%B5%D0%B4%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D0%B8).
1. Дата — не ранее трёх дней с текущей даты.
1. В поле фамилии и имени разрешены только русские буквы, дефисы и пробелы.
1. В поле телефона — только 11 цифр, символ + на первом месте.
1. Флажок согласия должен быть выставлен.

Тестируемая функциональность: отправка формы.

Поля «Город» и «Дата» заполняются через прямой ввод значений без использования выбора из выпадающего списка и всплывающего календаря.

Условия: если все поля заполнены корректно, то форма переходит в состояние загрузки:

![](pic/loading.png)

Важно: состояние загрузки не должно длиться более 15 секунд.

После успешной отправки формы появится всплывающее окно об успешном завершении бронирования:

![](pic/popup.png)

Вам необходимо самостоятельно изучить элементы на странице, чтобы подобрать правильные селекторы. Обратите внимание, что элементы могут быть как скрыты, так и динамически добавляться или удаляться из DOM.

<details>
    <summary>Подсказка</summary>

    Смотрите на `data-test-id`, но помните, что он может быть не у всех элементов.
</details>

<details>
    <summary>Ловушка 😈</summary>

    Дата и время всегда будут уязвимым местом ваших тестов. Ключевая ловушка в том, что, если вы их захардкодите, то тест, который работал сегодня, уже может не работать завтра, через неделю, месяц, потому что дата может перейти в разряд условного прошлого для приложения и стать невалидной.

    Кроме того, дата и время — это одно из немногих мест в тестах, где вам **иногда** придётся писать логику.
</details>
