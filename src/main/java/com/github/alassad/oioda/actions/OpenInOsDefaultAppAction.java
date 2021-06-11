package com.github.alassad.oioda.actions;

import com.intellij.ide.actions.RevealFileAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author yulinying
 * @email yulinying_1994@outlook.com
 */
public class OpenInOsDefaultAppAction extends AnAction {
    
    @Override
    public void actionPerformed(AnActionEvent event) {
        
        VirtualFile vFile = RevealFileAction.findLocalFile(event.getData(CommonDataKeys.VIRTUAL_FILE));
        if (vFile == null) {
            notify("selected file is empty", MessageType.INFO);
            return;
        }
        File file = new File(vFile.getPath());
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            notify("cannot open file", MessageType.ERROR);
        }
    }
    
    private static void notify(@Nonnull String content, @Nonnull MessageType type) {
        Notification notification = NotificationGroupManager.getInstance()
                .getNotificationGroup("com.github.alassad.oioda.notify")
                .createNotification(content, type);
        Notifications.Bus.notify(notification);
    }
    
    
}
