fullscreen-popupmenu
====================

Similar to MIUI menu effect. Full screen, Animation, Home key...

How To Use
-------------------------------
```java
mPopupMenu = new FullScreenPopupMenu(activity, menuItemListener, menuAdapter, parentView);
```

Interfaces
------------------------------
```java
mPopupMenu.onKeycodeMenuPressed();
mPopupMenu.startHomeWatcher();
mPopupMenu.stopHomeWatcher();
```
See details in Demo.