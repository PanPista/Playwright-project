package com.serenitydojo.playwright.todomvc;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.serenitydojo.playwright.fixtures.ChromeHeadlessOptions;
import com.serenitydojo.playwright.todomvc.pageobjects.TodoMvcAppPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@DisplayName("Adding and deleting todo items to the list")
@Feature("Adding and deleting todo items to the list")
@UsePlaywright(ChromeHeadlessOptions.class)
class AddingAndDeletingTodoItemsTest {

    private static final String FEED_THE_CAT = "Feed the cat";
    private static final String WALK_THE_DOG = "Walk the dog";
    private static final String BUY_SOME_MILK = "Buy some milk";
    private static final String FEED_THE_CAT_IN_CHINESE = "喂猫";
    private static final String FEED_THE_CAT_IN_ARABIC = "إطعام القط";
    private static final String EMPTY_TODO = "";
    private static final String TODO_INPUT_PLACEHOLDER = "What needs to be done?";
    TodoMvcAppPage todoMvcApp;

    private static final String[] INITIAL_ITEMS = {FEED_THE_CAT, WALK_THE_DOG, BUY_SOME_MILK};
    private static final String[] DUPLICATE_ITEMS = {FEED_THE_CAT, WALK_THE_DOG, FEED_THE_CAT};
    private static final String[] NON_ENGLISH_ITEMS = {
            FEED_THE_CAT, FEED_THE_CAT_IN_CHINESE, FEED_THE_CAT_IN_ARABIC
    };

    @BeforeEach
    void openApp(Page page) {
        todoMvcApp = new TodoMvcAppPage(page);
        todoMvcApp.open();
    }

    @Story("When the application starts")
    @DisplayName("When the application starts")
    @Nested
    class WhenTheApplicationStarts {
        @DisplayName("The list should be empty")
        @Test
        void the_list_should_initially_be_empty() {
            // TODO: Implement me
            // 1) Verify that no items are displayed in the todo list
            Assertions.assertThat(todoMvcApp.todoItemsDisplayed()).isEmpty();
        }

        @DisplayName("The user should be prompted to enter a todo item")
        @Test
        void the_user_should_be_prompted_to_enter_a_value() {
            // TODO: Implement me
            // 1) Verify that the input field is visible
            // 2) Verify that the placeholder text is "What needs to be done?"
            assertThat(todoMvcApp.getInputField()).isVisible();
            assertThat(todoMvcApp.getInputField()).hasAttribute("placeholder", TODO_INPUT_PLACEHOLDER);
        }
    }

    @Story("When we want to add item to the list")
    @DisplayName("When we want to add item to the list")
    @Nested
    class WhenAddingItems {

        @DisplayName("We can add a single item")
        @Test
        void addingASingleItem() {
            // TODO: Implement me
            // 1) Add a single todo item "Feed the cat"
            // 2) Verify that the list contains exactly "Feed the cat"
            todoMvcApp.addTodoItem(FEED_THE_CAT);

            assertThat(todoMvcApp.getTodoItems()).hasText(FEED_THE_CAT);
        }

        @DisplayName("We can add multiple items")
        @Test
        void addingSeveralItem() {
            // TODO: Implement me
            // 1) Add multiple items "Feed the cat" and "Walk the dog"
            // 2) Verify that the list contains exactly "Feed the cat" and "Walk the dog"
            todoMvcApp.addTodoItems(FEED_THE_CAT, WALK_THE_DOG);

            assertThat(todoMvcApp.getTodoItems())
                    .hasText(new String[] {FEED_THE_CAT, WALK_THE_DOG});
        }

        @DisplayName("We can't add an empty item")
        @Test
        void addingAnEmptyItem() {
            // TODO: Implement me
            // 1) Add a valid item "Feed the cat"
            // 2) Attempt to add an empty item
            // 3) Verify that the list contains only "Feed the cat"
            todoMvcApp.addTodoItems(FEED_THE_CAT, EMPTY_TODO);

            assertThat(todoMvcApp.getTodoItems()).hasText(FEED_THE_CAT);
        }

        @DisplayName("We can add duplicate items")
        @Test
        void addingDuplicateItem() {
            // TODO: Implement me
            // 1) Add items "Feed the cat", "Walk the dog", and "Feed the cat" again
            // 2) Verify that the list contains duplicates in the order they were added
            todoMvcApp.addTodoItems(DUPLICATE_ITEMS);

            assertThat(todoMvcApp.getTodoItems()).hasText(DUPLICATE_ITEMS);
        }

        @DisplayName("We can add items with non-English characters")
        @Test
        void addingNonEnglishItems() {
            // TODO: Implement me
            // 1) Add items in various languages (e.g., "Feed the cat", "喂猫", "إطعام القط")
            // 2) Verify that each item appears in the list as added
            todoMvcApp.addTodoItems(NON_ENGLISH_ITEMS);

            assertThat(todoMvcApp.getTodoItems())
                    .hasText(NON_ENGLISH_ITEMS);
        }
    }

    @Story("When we want to delete item in the list")
    @DisplayName("When we want to delete item in the list")
    @Nested
    class WhenDeletingItems {

        @DisplayName("We can delete an item in the middle of the list")
        @Test
        void deletingAnItemInTheMiddleOfTheList() {
            // TODO: Implement me
            // 1) Add items "Feed the cat", "Walk the dog", "Buy some milk"
            // 2) Delete "Walk the dog"
            // 3) Verify that the list contains "Feed the cat" and "Buy some milk"
            todoMvcApp.addTodoItems(INITIAL_ITEMS);
            todoMvcApp.deleteTodoItem(WALK_THE_DOG);

            assertThat(todoMvcApp.getTodoItems())
                    .hasText(new String[] {FEED_THE_CAT, BUY_SOME_MILK});
        }

        @DisplayName("We can delete an item at the end of the list")
        @Test
        void deletingAnItemAtTheEndOfTheList() {
            // TODO: Implement me
            // 1) Add items "Feed the cat", "Walk the dog", "Buy some milk"
            // 2) Delete "Buy some milk"
            // 3) Verify that the list contains "Feed the cat" and "Walk the dog"
            todoMvcApp.addTodoItems(INITIAL_ITEMS);
            todoMvcApp.deleteTodoItem(BUY_SOME_MILK);

            assertThat(todoMvcApp.getTodoItems())
                    .hasText(new String[] {FEED_THE_CAT, WALK_THE_DOG});
        }

        @DisplayName("We can delete an item at the start of the list")
        @Test
        void deletingAnItemAtTheStartOfTheList() {
            // TODO: Implement me
            // 1) Add items "Feed the cat", "Walk the dog", "Buy some milk"
            // 2) Delete "Feed the cat"
            // 3) Verify that the list contains "Walk the dog" and "Buy some milk"
            todoMvcApp.addTodoItems(INITIAL_ITEMS);
            todoMvcApp.deleteTodoItem(FEED_THE_CAT);

            assertThat(todoMvcApp.getTodoItems())
                    .hasText(new String[] {WALK_THE_DOG, BUY_SOME_MILK});
        }
    }
}
