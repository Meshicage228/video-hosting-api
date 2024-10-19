# SESSION STARTER

---

# О проекте:

Чтобы протестировать работоспособность стартера в данном проекте, необходимо:

1. Запустить `docker-compose`
2. Опубликовать springBoot-sessions-starter в свой локальный maven-repository
3. Выполнить таску `build` для root модуля `video-hosting-api`
4. Запустить микросервисный модуль : `session-service`
5. Запустить root модуль `video-hosting-api`

Все действия небходимо выполнить в указанном порядке.

---
# О стартере:

Для корректной работы данного стартера необходимо ознакомиться со следующими классами :

1. Аннотация `SessionProvider`
2. Интерфейс `BlackListProvider`
3. Класс для расширения : `SessionRequest`


## Как оно работает?

Для работы необходимо : 

1. Аннотировать любой метод при помощи `@SessionProvider`
2. Аннотируемый метод `ДОЛЖЕН` среди списка принимаемых параметров иметь объект, расширяющий `SessionRequest`
3. Передать в теле запроса / параметра `login` : При его отсутствии/пустоты будет выброшено исключение `BadRequestException`
. Именно через login будет вставлена сессия.

Следующий пример демонстрирует корректное использование стартера:
```
    @PutMapping("/{userId}")
    @SessionProvider(blackLists = {"Vlad", "Dmitry"}, defaultBlackListProviderEnabled = false)
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId,
                                              @RequestBody UserUpdateDto updateDto) {
        return ResponseEntity.ok(userService.fullUpdateUser(userId, updateDto));
    }
```

, где :

```
    class UserUpdateDto extends SessionRequest {
    }
```

Результатом успешной работы стартера является объект с дополнительным вставленным полем : `Session`

Если вы хотите отключить работу стартера, вам необходимо указать в .yaml/.properties файле :

```
session:
  provider:
    enabled: false
```

Префикс `session.provider.enabled` по умолчанию стоит `true`



---

## Black List logins

Аннотация `@SessionProvider` содержит параметр : `blackList` - черный список логинов, 
для которых Session вставлен не будет.

`blackList` можно перечислить через .yaml/.properties файлы : 

```
session:
  black-list:
    - "Egor"
    - "Andrey"
```

Префикс `session.black-list` опционален. Если вы не хотите, чтобы стартер считывал черный список
логинов с .yaml/.properties файла, укажите в аннотации `defaultBlackListProviderEnabled = false`

```
@SessionProvider(blackLists = {"Vlad", "Dmitry"}, defaultBlackListProviderEnabled = false)
```

В вышеупомянутом примере черный список будет сформирован только из `blackLists`, указанном в аннотации, при этом
`black-list` в .yaml/.properties будет проигнорирован.

---

Если вы хотите указать собственный источник черного списка логинов, то вам необходимо реализовать в вашем классе
Интерфейс `BlackListProvider` :

```
public class BlackListFromBD implements BlackListProvider {

private final BlackListRepository repository;

    @Override
    public Set<String> getBlackList() {
        return repository.getAllBlockedLogins();
    }
}
```

После этого вы можете указать собственную реализацию в качестве параметра аннотации : 
```
@SessionProvider(blackListProviders = BlackListFromBD.class)
```

В вышеупомянутом примере черный список будет сформирован из черного списка вашей реилизации, 
а также из .yaml/.properties файла, если вы их указали.

Собственный источник черного списка также возможно указать и через .yaml/.properties файл :
Необходимо указать путь от корневого источника, например :

```
session:
  property-providers: ru.clevertec.providers.BlackListFromBD
```

---
## Микросервис : session-service

Для поиска/создания нужной сессии по логину используется данный микросервис.

При отсутствии в базе данных нужного сессии с требуемым логином, будет создана и отдана новая сессия.

Микросервис по умолчанию раз в день очищает записи в базе данных, если вам необходимо выключить очистку
вы можете изменить это в .yaml/.properties файле, а также и частоту очистки :

```
app:
  session:
    clean:
     enabled: true
     frequency: "0 0 * * * ?"
```

По умолчанию очистка включена, а частота раз в день в полночь