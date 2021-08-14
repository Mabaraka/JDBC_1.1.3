import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private final UserService userService = new UserServiceImpl();

    private final String testName = "Ivan";
    private final String testLastName = "Ivanov";
    private final byte testAge = 5;


    @Test
    public void dropUsersTable() {
        try {
            userService.dropTable();
            userService.dropTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userService.dropTable();
            userService.createTable();
        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testName, testLastName, testAge);

            User user = userService.getAll().get(0);

            if (!testName.equals(user.getFirstName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testName, testLastName, testAge);
            userService.delete(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testName, testLastName, testAge);
            List<User> userList = userService.getAll();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userService.dropTable();
            userService.createTable();
            userService.save(testName, testLastName, testAge);
            userService.cleanTable();

            if (userService.getAll().size() != 0) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }

}
