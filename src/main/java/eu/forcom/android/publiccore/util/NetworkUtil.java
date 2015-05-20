package eu.forcom.android.publiccore.util;

import android.net.Uri;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkUtil {

    /**
     * Gets the primary IP address of the device
     *
     * http://stackoverflow.com/questions/6064510/how-to-get-ip-address-of-the-device
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the host from given url
     */
    public static String getHostFromUrl(String url) {
        Uri uri = Uri.parse(url);

        return uri.getHost();
    }
}
