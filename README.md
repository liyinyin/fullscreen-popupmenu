fullscreen-popupmenu
====================

Similar to MIUI menu effect. Full screen, Animation, Home key...

How To Use
-------------------------------
```
mPopupMenu = new FullScreenPopupMenu(activity, menuItemListener, menuAdapter, parentView);

```

Interfaces
------------------------------
```
mPopupMenu.onKeycodeMenuPressed();
mPopupMenu.startHomeWatcher();
mPopupMenu.stopHomeWatcher();

```
See details in testDemo.