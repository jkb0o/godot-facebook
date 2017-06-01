def can_build(platform):
	return platform == "android"


def configure(env):
	if env['platform'] == 'android':
		env.android_add_dependency("compile 'com.android.support:appcompat-v7:23.0.1'")
		env.android_add_dependency("compile 'com.facebook.android:facebook-android-sdk:4.22.1'")
		env.android_add_java_dir("android")
		env.android_add_to_manifest("AndroidManifestChunk.xml")
