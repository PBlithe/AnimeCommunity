# 小宅子后台管理系统

## 账号

### 创建管理员

拥有创建权限的管理员可以创建新的管理员。

管理员可以创建一个新的账号，账号可以是可以是数字字母下划线的组合长度要在5-10之间。密码必须在6位到15位之间。权限有处理违规（默认勾选）、查看日志（默认勾选）、发送推送、写动漫文章、帖子管理、话题管理、创建管理员、修改账号、删除账号。

### 修改账号

拥有修改账号权限的管理员才能修改账号，拥有删除权限的管理员才能权限删除已经创建好的管理员账号。所有管理员用户均有权限查看其他管理员的权限。

### 查询用户信息

所有管理员都有权限查看用户信息。用户信息包含用户ID、昵称、手机号、用户关注的人数、用户关注哪些人、用户的粉丝数、有哪些人关注了该用户、用户发了多少帖子、帖子的详细内容、用户关注的话题数量、用户具体关注了哪些话题。

管理员可以通过用户ID、昵称或者手机号快速查看相关用户。

### 处理违规账号

拥有处理违规账号权限的管理员才可以查看。违规账号的信息包括用户ID、昵称、手机号、封号操作和解除操作。管理员可以封了某个用户的账号，被封了的用户将再也无法登录并注册该社区。被封了的账号所有的记录都将保存在社区系统中不删除，为了防止管理员错误封号，被封了账号的用户可以联系客服，如果确认确实错误封号了话可以解除。

## 推送

### 警告

后台管理员可以给违规社区规定的用户发送警告，如果用户被多次警告后管理员可以封了该用户的账号。管理员可以通过两种方式发送警告。第一种，通过手机号，管理员选择发送警告的理由和补充说明即可。第二种，通过用户ID，管理员选择发送警告的理由和补充说明即可。管理员每次给用户发送警告都会记录在系统中，如果用户被警告多次，系统将会自动封号。

### 公告

后台管理员可以通过公告给所有用户发送消息。无论是哪个账号的管理员，普通用户端显示的发送者均为“小宅子管理员”。管理员输入标题和内容即可发送公告。标题和内容均不能为空。

## 帖子/话题

###  帖子操作

管理员可以通过序号、用户ID、手机号、特定的事件范围、标题或者动漫标签快速地选择相关帖子内容，也可直接显示全部内容。

帖子操作的数据表格内容有帖子的序号、标题、内容、所属话题、帖子创建者的ID、创建这的手机号、创建者的昵称、创建时间、最新的更改时间、置顶操作和删除操作。管理员点击详细内容可以看见帖子的内容和回复帖子的用户。

### 动漫标签操作

管理员可以通过序号、昵称和创建人快速搜索相关动漫标签，也可以直接显示全部内容。

动漫标签操作表格有动漫标签的序号、名称、动漫标签里帖子的数量、动漫标签的创建人、创建的时间、最近一个修改的时间、动漫标签的相关样式、修改动漫标签的操作和删除动漫标签的操作。

## 文章

管理员可以写文章发表到相关的动漫标签下。管理员需要选择相关的动漫标签、标题和内容，三者都必填才能可以发表成功。

## 举报

普通用户举报的用户将在此显示。管理员受理了相关的举报信息点击标记即可。

数据表格中有举报列表的序号、用户举报的理由、补充说明、举报人、被举报的人、举报的时间、帖子/回复ID、类型、状态、内容、标记操作和受理操作。列表序号、举报人、被举报人和时间可以排序。点击内容列的查看选项可以查看具体信息。

## 日志

### 创建账号记录

记录了所有的创建修改管理员的记录。考虑到管理员创建修改的记录并不会很多，因此这里不添加快速搜索功能。表结构有序号、管理员账号、时间、操作类型、操作人和补充说明。[每个数据字段的功能请结合超级管理系统模块种t_admin_logs数据表的说明。](#t_admin_logs)

### 警告推送记录

记录了管理员推送警告给普通用户的记录。管理员可以通过警告理由下拉框、手机号、用户ID或者发送人快速搜索相关记录。

数据表格的内容有：序号、发送对象的手机号、发送对象的用户ID、发送警告的原因、补充说明、发送人和发送时间。

序号和发送时间可以排序。

### 公告推送记录

记录了管理员发送了哪些公告。

管理员可以通过标题、发送人和时间范围快速搜索相关记录。

数据表格的列表有序号、标题、内容、发送人和时间。

序号和时间可以排序。

### 话题修改记录

记录了管理员创建修改删除话题的记录。

管理员可以通过标题的序号、修改人和时间快速搜索相关内容。

数据表格的内容有：序号、动漫标签序号、修改人、时间、操作详情、创建操作、修改操作和删除操作。

### 帖子修改记录

记录了管理员修改帖子的记录。

管理员可以通过帖子序号、修改人和时间范围快速搜索相关内容。

数据表格的内容有：序号、帖子序号、修改人、时间、置顶操作和删除操作。

序号、帖子序号和时间可以排序。

### 处理违规账号记录

管理员处理违规账号的所有记录。

管理员可以通过用户ID、手机号和时间快速搜索相关内容。

数据表格的内容有：序号、用户ID、手机号、时间、封号操作和解除账号操作。

序号和时间可排序。



# 模块设计

## 超级管理系统模块

### 数据库设计

#### t_admin管理员数据表

| 字段     | 注释                                                         |
| -------- | ------------------------------------------------------------ |
| no       | 序号。一个索引，用于排序。                                   |
| id       | 账号的ID，用户登录，字符串类型，可以是数字字母下划线的组合长度5-10位 |
| password | 密码。用AES_ENCRYPT和密钥ANIME-ADMIN加密                     |
| time     | 时间记录，一个timestamp类型，只记录账号创建的时间            |
| auth     | 账户的权限。记录当前账户中有哪些权限，连接权限数据表         |
| optor    | 操作人，只记录账号是由谁创建的                               |
| state    | 目前账户的状态。NORMAL/BLOCK (正常/封锁)                     |



#### t_auth权限数据表

| 字段 | 注释                            |
| ---- | ------------------------------- |
| id   | 序号。一个索引，用于排序。      |
| a_id | 权限，一个varchar类型，可重复。 |
| auth | 权限具体类型，ENUM类型。        |

权限的具体类型有10种。

```
ChuLiWeiGui, // 对应 1
ChaKanRiZhi,// 对应 2
TuiSong,// 对应 3
WenZhang,// 对应 4
ChaXunYongHuXinXi,// 对应 5
TieZi,// 对应 6
DongManBiaoQian,// 对应 7
XiuGaiZhangHao,// 对应 8
ShanChuZhangHao,// 对应 9
CreateAdmin,// 对应 0
```

#### <span id="t_admin_logs">t_admin_logs管理员创建记录表</span>

| 字段   | 注释                                                         |
| ------ | ------------------------------------------------------------ |
| no     | 序号。一个索引，用于排序。                                   |
| id     | 账号的ID，字符串类型，可以是数字字母下划线的组合长度5-10位   |
| time   | 时间记录，一个timestamp类型，用于记录账号创建或者修改的时间  |
| opt    | 操作，一个ENUM值。CREATE/MODIFY/DELETE/BLOCK (创建/修改/删除/封锁) |
| optor  | 操作人，记录账号被谁创建或者修改。                           |
| supple | 字符串类型，记录修改了增加或删除了账号的哪些权限             |


