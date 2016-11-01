import { NativeModules } from 'react-native';

const umeng = NativeModules.UmengShare;

//laod pic react-native 低版本bug
//var resolveAssetSource = require('../react-native/Libraries/Image/resolveAssetSource');

export default class UmengShare {

	static setWeixin(appId, secret) {
		umeng.setWeixin(appId, secret);
	}

	static setQQZone(appId, secret) {
		umeng.setQQZone(appId, secret);
	}

	static setSinaWeibo(appId, secret) {
		umeng.setSinaWeibo(appId, secret);
	}

	static openShare(title, content, url, imageSource){
		//umeng.openShare(title, content, url, resolveAssetSource(imageSource));
		umeng.openShare(title, content, url, imageSource);
	}
}