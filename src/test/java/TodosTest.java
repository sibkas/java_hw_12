import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldReturnEmptyWhenNoMatch() {
        Todos todos = new Todos();

        Task[] actualEmpty = todos.search("Несуществующий запрос");
        assertEquals(0, actualEmpty.length);
    }


    @Test
    public void searchReturnsEmptyWhenSimpleTaskDoesNotMatch() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(simpleTask);
        Task[] expected = {simpleTask};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllReturnsAllAddedSimpleTasks() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить родителям");
        Todos todos = new Todos();
        todos.add(simpleTask);
        Task[] actual = todos.search("друзьям");
        assertEquals(0, actual.length);
    }

    @Test
    public void todosSearchReturnsEpicWhenSubtaskMatches() {
        Todos todos = new Todos();

        Epic epic = new Epic(1, new String[]{"Молоко", "Яйца", "Хлеб"});
        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Яйца");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsEmptyWhenEpicHasNoMatchingSubtasks() {
        Todos todos = new Todos();

        Epic epic = new Epic(1, new String[]{"Молоко", "Яйца", "Хлеб"});
        todos.add(epic);

        Task[] actual = todos.search("соль");
        assertEquals(0, actual.length);

    }

    @Test
    public void todosSearchReturnsMeetingWhenTopicMatches() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(1, "Встреча по проекту", "НетоБанк", "утром");
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Встреча");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void todosSearchReturnsMeetingWhenProjectMatches() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(1, "Встреча по проекту", "НетоБанк", "утром");
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("НетоБанк");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void addingDuplicateTasks() {
        Todos todos = new Todos();

        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        todos.add(simpleTask);
        todos.add(simpleTask); // добавляем повторно

        Task[] expected = {simpleTask, simpleTask};
        Task[] actual = todos.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchWithEmptyQueryReturnsAllTasks() {
        Todos todos = new Todos();

        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "НетоБанк", "утром");
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsMeetingWhenTopicContainsQuery() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "НетоБанк", "утром");

        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Обсуждение");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchReturnsEmptyWhenMeetingDoesNotMatch() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "НетоБанк", "утром");

        todos.add(meeting);

        Task[] actual = todos.search("вечер");
        assertEquals(0, actual.length);
    }

    @Test
    public void shouldReturnSubtasksArray() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        String[] actualSubtasks = epic.getSubtasks();

        assertArrayEquals(subtasks, actualSubtasks);
    }

    @Test
    public void simpleTaskGetTitleReturnsCorrectTitle() {
        String expectedTitle = "Позвонить родителям";
        SimpleTask simpleTask = new SimpleTask(1, expectedTitle);

        String actualTitle = simpleTask.getTitle();

        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void meetingGetTopicReturnsCorrectTopic() {
        String expectedTopic = "Встреча по проекту";
        Meeting meeting = new Meeting(2, expectedTopic, "НетоБанк", "утром");

        String actualTopic = meeting.getTopic();

        assertEquals(expectedTopic, actualTopic);
    }

    @Test
    public void meetingGetProjectReturnsCorrectProject() {
        String expectedProject = "НетоБанк";
        Meeting meeting = new Meeting(2, "Встреча по проекту", expectedProject, "утром");

        String actualProject = meeting.getProject();

        assertEquals(expectedProject, actualProject);
    }


    @Test
    public void meetingGetStartReturnsCorrectStart() {
        String expectedStart = "утром";
        Meeting meeting = new Meeting(2, "Встреча по проекту", "НетоБанк", expectedStart);

        String actualStart = meeting.getStart();

        assertEquals(expectedStart, actualStart);
    }

    @Test
    public void getIdReturnsCorrectId() {
        int expectedId = 42;
        Task task = new Task(expectedId);

        int actualId = task.getId();

        assertEquals(expectedId, actualId);
    }




}