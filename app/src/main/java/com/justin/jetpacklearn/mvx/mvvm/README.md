* MVVM
  MVVM架构的核心思想是将应用程序划分为三个主要部分：Model、View和ViewModel。

舍弃了MVP中的接口View

View层持有VM，监听VM中的状态数据变更，从而对view发生变更，
VM持有M