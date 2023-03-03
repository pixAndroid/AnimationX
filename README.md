
# AnimationX 
[![](https://jitpack.io/v/pixAndroid/AnimationX.svg)](https://jitpack.io/#pixAndroid/AnimationX)


Mainly focused on regular animations, such as - Aplha, Translate, Scale, etc.


## Implementation

#### Step 1. Add the JitPack repository to your build.gradle file

```bash
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
```
#### Step 2. Add the dependency

```bash
	dependencies {
	        implementation 'com.github.pixAndroid:AnimationX:1.8'
	}
```

## Smooth ProgressBar Animation
```bash
    //SET MAX PROGRESS 
	int yourMaxValue = 20000;
    progress_bar.setMax(yourMaxValue * 100);
    
    //DO ANIMATION
    AnimationX.smoothProgressAnimation(progress_bar, progress, 7000);
```

## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


