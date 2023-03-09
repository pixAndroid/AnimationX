
# AnimationX 
[![](https://jitpack.io/v/pixAndroid/AnimationX.svg)](https://jitpack.io/#pixAndroid/AnimationX)


Mainly focused on regular animations, such as - Aplha, Translate, Scale, etc.



### Project Requirements
distributionUrl=https\://services.gradle.org/distributions/gradle-7.4-bin.zip



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
	implementation 'com.github.pixAndroid:AnimationX:2.3'
```
## Examples
### 01 - Smooth ProgressBar Animation
```bash
    //SET MAX PROGRESS 
    int yourMaxValue = 20000;
    progress_bar.setMax(yourMaxValue * 100);
    
    //DO ANIMATION
    AnimationX.smoothProgressAnimation(progress_bar, progress, duration);
```

### 02 - Digit Animation
```bash
     AnimationX.digitCounter(0, 100, txt_number);
```

### 03 - Color Chnage Animation
```bash
     AnimationX.text_color_change_effect(MainActivity.this, text_view, 0, Color.RED, 500);
```

### 04 - Spin & Move Animation
```bash
     AnimationX.spin_and_move(view);
```

### 05 - Fade In & Fade Out Animation
```bash
     AnimationX.fade_in(view, 2000);
     AnimationX.fade_out(view, 2000);
```

## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


