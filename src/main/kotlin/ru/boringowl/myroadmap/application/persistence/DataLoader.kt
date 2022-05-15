package ru.boringowl.myroadmap.application.persistence

import org.springframework.stereotype.Component
import ru.boringowl.myroadmap.domain.Route
import ru.boringowl.myroadmap.infrastructure.jpa.JpaRoute

@Component
class DataLoader constructor(val repo: RouteRepo) {

    init {
        LoadRoutes()
    }

    private fun LoadRoutes() {
        Routes.values().forEach {
            repo.save(JpaRoute(it.route))
        }
    }
}

enum class Routes(val route: Route) {
    ANDROID(Route(1,"Android", descriptions[0])),
    FRONTEND(Route(2, "Frontend", descriptions[1])),
    JAVA(Route(3, "Java", descriptions[2])),
    SYSADM(Route(4, "Системный администратор", descriptions[3])),
    TESTS(Route(5, "Тестировщик", descriptions[4])),
    ONE_C(Route(6, "1С", descriptions[5])),
    DOTNET(Route(7, ".NET", descriptions[6])),
    CPLUS(Route(8, "C++", descriptions[7])),
    IOS(Route(9, "iOS", descriptions[8])),
    PYTHON(Route(10, "Python", descriptions[9])),
    DEVOPS(Route(11, "Devops", descriptions[10])),
    DATA_SCIENCE(Route(13, "Data scientist", descriptions[11])),
    FULLSTACK(Route(14, "Fullstack", descriptions[12]))
}

val descriptions = arrayListOf(
    "Android-разработчик создает, обновляет, усовершенствует мобильные приложения для смартфонов, планшетов, электронных книг, игровых приставок и других девайсов, работающих на операционной системе Android.",
    "Frontend-разработчик (frontend developer) — это специалист, который отвечает за создание пользовательского интерфейса сайта, приложения или ПО.",
    "Java-разработчики — люди, которые освоили этот язык и одноимённую платформу, а теперь создают на ней программы. Java — это язык, подходящий для создания сайтов и серверов, мобильных приложений.",
    "Системный администратор — это специалист, который отвечает за работу информационной инфраструктуры компании, обеспечивает ее настройку, поддерживает работоспособность, занимается ее развитием и совершенствованием.",
    "Тестировщик или QA-инженер — специалист, который тестирует различные программы, приложения и сервисы, чтобы убедиться, что они работают корректно, выявить возможные ошибки и уязвимости в защите.",
    "Программисты 1С занимаются внедрением и сопровождением программ 1С в организациях: устанавливают и настраивают, дорабатывают и обновляют их, а ещё консультируют пользователей.",
    "Разработчик .NET отвечает за проектирование, настройку и разработку программных приложений с помощью фреймворка .NET, предоставляемого компанией Microsoft.",
    "Разработчик на C++ занимается разработкой высокопроизводительных и высоконагруженных систем, таких как поисковики, драйверы, игры и приложения.",
    "iOS-разработчик создаёт приложения для устройств Apple — онлайн-банки, навигаторы, фитнес-трекеры и другие полезные сервисы на языке Swift",
    "Python-разработчик — это специалист, создающий программы, приложения и код вообще на языке программирования Python. Он пишет мобильные и десктопные приложения, а также создает клиент-серверные программы",
    "DevOPS-инженер — это специалист, который синхронизирует этапы разработки программного продукта, автоматизирует задачи разработчиков и QA, умеет программировать и быстро изучает новые инструменты.",
    "Data Scientist – это специалист, который занимается поиском закономерностей в больших массивах данных, анализирует и хранит их с помощью алгоритмов машинного обучения и нейронных сетей.",
    "Full Stack Developer — это универсальный программист, который может сам с нуля разработать функциональный продукт. Такой специалист разбирается как в Back-end составляющей, так и во Front-end.",
)