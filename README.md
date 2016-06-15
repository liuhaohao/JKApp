####项目名：掘坑
####开发工具：eclipse
####Android API Target：19

###功能性模块划分：
* 首页列表（包括详情，点赞）                  ------ 刘豪
* 发布                                        ------ 何俊宇
* 评论功能（包括回复）                        ------ 廖骏鸿
* 个人相关（登录注册，个人信息，我的发布）    ------ 廖鹏如
* 主体框架，设置，工具类等。                  ------ 麦灿标


###数据库相关：
data表，comment表，user表

注意：使用主外键进行表关联


###开发任务说明
------
* 首页列表：实现MainActiivty中的列表ListView功能，实现
* 发布：实现PublishActiivty类的功能
* 评论：详情页面点击评论进入评论Activity，实现该Activity功能
* 个人相关：LoginActivity，RegisterActivity，侧栏菜单个人信息显示，实现PersonalityActivity功能。
* 主体框架：已实现（差美化UI），实现SettingsActivity功能。

注意：

* 所有图片显示都使用UIL。
* 可使用EventBus进行事件通知
* 使用SQLite只需要两步：1.在App类中的init方法classList添加表对应的实体类 2.在需要进行增删改查
  的地方调用OrmDatabaseHelper类的getTDao方法拿到相应的Dao，然后就可以进行增删改查。
* SharePreferences没有封装，需要自己调用原生API。
* 如需磁盘缓存可考虑使用DiskLruCache
* 如需查看图片（包括拖拽，放大缩小）可考虑使用PhotoView
* 下拉ListView可使用XListView


###Git协作说明
------
* 每开发者对应一个develop-xxx分支。每次开发时，需要从develop分支将最新的更新pull到本地，
然后进行开发，开发完将更新push到对应的develop-xxx分支上，最后需要将更新合并到develop
上（如果有冲突要解决，保证程序能够正常运行起来）
* 只有阶段性开发任务完成才会把develop的更新合并到master分支上。
* 每次commit请写相应有意义的log。

