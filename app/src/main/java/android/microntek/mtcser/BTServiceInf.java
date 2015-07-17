package android.microntek.mtcser;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface BTServiceInf extends IInterface {

    public static abstract class Stub extends Binder implements BTServiceInf {

        private static class Proxy implements BTServiceInf {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public void answerCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void avPlayNext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void avPlayPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void avPlayPrev() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void avPlayStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void connectBT(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void connectOBD(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteBT(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteHistory(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeInt(i);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteHistoryAll() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteOBD(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void dialOut(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void dialOutSub(char c) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeInt(c);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void disconnectBT(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void disconnectOBD(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte getAVState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    byte readByte = obtain2.readByte();
                    return readByte;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getAutoAnswer() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return z;
            }

            public boolean getAutoConnect() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
                return z;
            }

            public byte getBTState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    byte readByte = obtain2.readByte();
                    return readByte;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCallInNum() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    List<String> createStringArrayList = obtain2.createStringArrayList();
                    return createStringArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getDialOutNum() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getHistoryList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    List<String> createStringArrayList = obtain2.createStringArrayList();
                    return createStringArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getMatchList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    List<String> createStringArrayList = obtain2.createStringArrayList();
                    return createStringArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getModuleName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getModulePassword() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getNowDevAddr() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getNowDevName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getPhoneBookList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    List<String> createStringArrayList = obtain2.createStringArrayList();
                    return createStringArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getPhoneNum() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hangupCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void init() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void musicMute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void musicUnmute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void rejectCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void scanStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void scanStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAutoAnswer(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAutoConnect(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setModuleName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setModulePassword(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    obtain.writeString(str);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void switchVoice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void syncMatchList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void syncPhonebook() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.microntek.mtcser.BTServiceInf");
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "android.microntek.mtcser.BTServiceInf");
        }

        public static BTServiceInf asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.microntek.mtcser.BTServiceInf");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof BTServiceInf)) ? new Proxy(iBinder) : (BTServiceInf) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            byte bTState;
            String dialOutNum;
            boolean z = false;
            boolean autoConnect;
            List matchList;
            switch (i) {
                case 1:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    init();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    bTState = getBTState();
                    parcel2.writeNoException();
                    parcel2.writeByte(bTState);
                    return true;
                case 3:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    bTState = getAVState();
                    parcel2.writeNoException();
                    parcel2.writeByte(bTState);
                    return true;
                case 4:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutNum = getDialOutNum();
                    parcel2.writeNoException();
                    parcel2.writeString(dialOutNum);
                    return true;
                case 5:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutNum = getCallInNum();
                    parcel2.writeNoException();
                    parcel2.writeString(dialOutNum);
                    return true;
                case 6:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutNum = getPhoneNum();
                    parcel2.writeNoException();
                    parcel2.writeString(dialOutNum);
                    return true;
                case 7:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    long nowDevAddr = getNowDevAddr();
                    parcel2.writeNoException();
                    parcel2.writeLong(nowDevAddr);
                    return true;
                case 8:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutNum = getNowDevName();
                    parcel2.writeNoException();
                    parcel2.writeString(dialOutNum);
                    return true;
                case 9:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    avPlayPause();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    avPlayStop();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    avPlayPrev();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    avPlayNext();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    answerCall();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    hangupCall();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    rejectCall();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    switchVoice();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    syncPhonebook();
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutNum = getModuleName();
                    parcel2.writeNoException();
                    parcel2.writeString(dialOutNum);
                    return true;
                case 19:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutNum = getModulePassword();
                    parcel2.writeNoException();
                    parcel2.writeString(dialOutNum);
                    return true;
                case 20:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    setModuleName(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    setModulePassword(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setAutoConnect(z);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    autoConnect = getAutoConnect();
                    parcel2.writeNoException();
                    if (autoConnect) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 24:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setAutoAnswer(z);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    autoConnect = getAutoAnswer();
                    parcel2.writeNoException();
                    if (autoConnect) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 26:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    connectBT(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    disconnectBT(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    connectOBD(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    disconnectOBD(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    deleteOBD(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    deleteBT(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    syncMatchList();
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    matchList = getMatchList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(matchList);
                    return true;
                case 34:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    matchList = getDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(matchList);
                    return true;
                case 35:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    matchList = getHistoryList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(matchList);
                    return true;
                case 36:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    matchList = getPhoneBookList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(matchList);
                    return true;
                case 37:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    deleteHistory(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    deleteHistoryAll();
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    musicMute();
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    musicUnmute();
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    scanStart();
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    scanStop();
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOut(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface("android.microntek.mtcser.BTServiceInf");
                    dialOutSub((char) parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("android.microntek.mtcser.BTServiceInf");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void answerCall() throws RemoteException;

    void avPlayNext() throws RemoteException;

    void avPlayPause() throws RemoteException;

    void avPlayPrev() throws RemoteException;

    void avPlayStop() throws RemoteException;

    void connectBT(String str) throws RemoteException;

    void connectOBD(String str) throws RemoteException;

    void deleteBT(String str) throws RemoteException;

    void deleteHistory(int i) throws RemoteException;

    void deleteHistoryAll() throws RemoteException;

    void deleteOBD(String str) throws RemoteException;

    void dialOut(String str) throws RemoteException;

    void dialOutSub(char c) throws RemoteException;

    void disconnectBT(String str) throws RemoteException;

    void disconnectOBD(String str) throws RemoteException;

    byte getAVState() throws RemoteException;

    boolean getAutoAnswer() throws RemoteException;

    boolean getAutoConnect() throws RemoteException;

    byte getBTState() throws RemoteException;

    String getCallInNum() throws RemoteException;

    List<String> getDeviceList() throws RemoteException;

    String getDialOutNum() throws RemoteException;

    List<String> getHistoryList() throws RemoteException;

    List<String> getMatchList() throws RemoteException;

    String getModuleName() throws RemoteException;

    String getModulePassword() throws RemoteException;

    long getNowDevAddr() throws RemoteException;

    String getNowDevName() throws RemoteException;

    List<String> getPhoneBookList() throws RemoteException;

    String getPhoneNum() throws RemoteException;

    void hangupCall() throws RemoteException;

    void init() throws RemoteException;

    void musicMute() throws RemoteException;

    void musicUnmute() throws RemoteException;

    void rejectCall() throws RemoteException;

    void scanStart() throws RemoteException;

    void scanStop() throws RemoteException;

    void setAutoAnswer(boolean z) throws RemoteException;

    void setAutoConnect(boolean z) throws RemoteException;

    void setModuleName(String str) throws RemoteException;

    void setModulePassword(String str) throws RemoteException;

    void switchVoice() throws RemoteException;

    void syncMatchList() throws RemoteException;

    void syncPhonebook() throws RemoteException;
}
