
# AnimationX 
[![](https://jitpack.io/v/pixAndroid/AnimationX.svg)](https://jitpack.io/#pixAndroid/AnimationX)

A comprehensive Android animation library focused on providing all possible animation work including regular animations, modern property animations, physics-based animations, and advanced UI effects.

![cover_bl – 12](https://github.com/pixAndroid/AnimationX/assets/36542546/7fd586c7-2640-4aba-8912-3a1641236e6c)

###Demo

https://github.com/pixAndroid/AnimationX/assets/36542546/634e7abc-19b3-4795-8932-8f65ee3f895b

### 🆕 What's New - Comprehensive Animation Upgrade

#### Enhanced Interpolator Support
- All animation types now support advanced interpolators: `LINEAR`, `ACCELERATE`, `DECELERATE`, `BOUNCE`, `OVERSHOOT`, `ANTICIPATE`, `ANTICIPATE_OVERSHOOT`, `CYCLE`

#### Physics-Based Animations
- **Spring Animations**: Natural, physics-based motion with customizable stiffness and damping
- **Dynamic Animations**: Smooth, responsive animations that react to user input

#### 3D Transformations
- **3D Rotation**: Rotate views on X, Y, and Z axes
- **Advanced Perspective**: Create depth and dimension in your animations

#### Morphing & Path Animations
- **View Morphing**: Smoothly change view dimensions and shapes
- **Path Following**: Animate views along custom paths

#### Modern Animation APIs
- **ViewPropertyAnimator Integration**: Hardware-accelerated animations
- **Enhanced Animation Chaining**: Create complex sequential and parallel animations
- **Circular Reveal**: Modern Material Design reveal animations (API 21+)

#### Advanced UI Effects
- **Parallax Effects**: Multi-layer scrolling animations
- **Flip Animations**: Horizontal and vertical card-flip effects
- **Elastic Animations**: Spring-loaded scale effects
- **Wave Effects**: Ocean-like motion patterns
- **Typewriter Effects**: Character-by-character text animations

#### Enhanced RecyclerView Animations
- **Slide Animations**: Left/right slide-in effects
- **Fade Animations**: Smooth alpha transitions
- **Enhanced Layout Animations**: More options for item animations

### Project Requirements
distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip



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
	implementation 'com.github.pixAndroid:AnimationX:5.0'
```
## Examples

### Basic Animations (Existing)

#### 01 - Smooth ProgressBar Animation
```java
    //SET MAX PROGRESS 
    int yourMaxValue = 20000;
    progress_bar.setMax(yourMaxValue * 100);
    
    //DO ANIMATION
    AnimationX.smoothProgressAnimation(progress_bar, progress, duration);
```

#### 02 - Digit Animation
```java
     AnimationX.digitCounter(0, 100, text_view);
```

#### 03 - Color Change Animation
```java
     AnimationX.text_color_change_effect(MainActivity.this, text_view, 0, Color.RED, 500);
```

#### 04 - Spin & Move Animation
```java
     AnimationX.spin_and_move(view);
```

#### 05 - Fade In & Fade Out Animation
```java
     AnimationX.fade_in(view, 2000);
     AnimationX.fade_out(view, 2000);
```

### 🆕 New Advanced Animations

#### 3D Rotation Animations
```java
    // Rotate on X-axis with bounce interpolator
    AnimationX.rotateX(view, 0f, 360f, 1000, AnimationX.InterpolatorType.BOUNCE, listener);
    
    // Rotate on Y-axis with overshoot
    AnimationX.rotateY(view, 0f, 180f, 800, AnimationX.InterpolatorType.OVERSHOOT, listener);
    
    // Rotate on Z-axis with anticipate
    AnimationX.rotateZ(view, 0f, 360f, 1200, AnimationX.InterpolatorType.ANTICIPATE, listener);
```

#### Physics-Based Spring Animations
```java
    // Spring animation with custom stiffness and damping
    AnimationX.springAnimation(view, DynamicAnimation.TRANSLATION_Y, 0f, 
                              SpringForce.STIFFNESS_MEDIUM, 
                              SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY, 
                              listener);
```

#### Modern Property Animations
```java
    // Modern fade in with interpolator
    AnimationX.modernFadeIn(view, 500, AnimationX.InterpolatorType.BOUNCE);
    
    // Modern scale animation
    AnimationX.modernScaleIn(view, 600, AnimationX.InterpolatorType.OVERSHOOT);
```

#### Flip Animations
```java
    // Horizontal card flip
    AnimationX.flipHorizontal(view, 1000, listener);
    
    // Vertical card flip
    AnimationX.flipVertical(view, 1000, listener);
```

#### Morphing Animations
```java
    // Morph view width
    AnimationX.morphWidth(view, currentWidth, newWidth, 800, 
                         AnimationX.InterpolatorType.OVERSHOOT, listener);
    
    // Morph view height
    AnimationX.morphHeight(view, currentHeight, newHeight, 800, 
                          AnimationX.InterpolatorType.BOUNCE, listener);
```

#### Circular Reveal (API 21+)
```java
    // Reveal animation from center
    AnimationX.circularReveal(view, centerX, centerY, 0f, finalRadius, 600, listener);
```

#### Advanced Animation Chaining
```java
    new AnimationX.AnimationChain()
        .addFadeIn(view, 300, AnimationX.InterpolatorType.DECELERATE)
        .addScaleUp(view, 400, AnimationX.InterpolatorType.BOUNCE)
        .addRotation(view, 360f, 500, AnimationX.InterpolatorType.OVERSHOOT)
        .setListener(listener)
        .start();
```

#### Enhanced Text Effects
```java
    // Typewriter effect
    AnimationX.typewriterEffect(textView, "Hello World!", 100, listener);
    
    // Wave animation
    AnimationX.waveAnimation(view, 2000, 50f, listener);
```

#### Enhanced RecyclerView Animations
```java
    // Slide in from left
    AnimationX.recyclerview_slide_in_left(recyclerView);
    
    // Slide in from right
    AnimationX.recyclerview_slide_in_right(recyclerView);
    
    // Fade in effect
    AnimationX.recyclerview_fade_in(recyclerView);
```

#### XML-Based Convenience Animations
```java
    // Wiggle animation
    AnimationX.wiggleAnimation(context, view, listener);
    
    // Elastic scale effect
    AnimationX.elasticScaleXML(context, view, listener);
    
    // Floating effect (continuous)
    AnimationX.floatingEffect(context, view);
```

#### Parallax Effects
```java
    // Simple parallax translation
    AnimationX.parallaxTranslate(view, scrollOffset, 0.5f);
```

#### Elastic Scale Animation
```java
    // Programmatic elastic scale
    AnimationX.elasticScale(view, 1.5f, 800, listener);
```

### Enhanced Existing Methods
All existing fade, alpha, and animation methods now support interpolator types:

```java
    // Enhanced fade with interpolator
    AnimationX.fade_in(view, 1000, AnimationX.InterpolatorType.BOUNCE, listener);
    
    // Enhanced alpha animation with interpolator
    AnimationX.alphaAnimation(view, 0f, 1f, 800, 
                             AnimationX.InterpolatorType.OVERSHOOT, listener);
```

## 🎯 Performance Features
- Hardware acceleration support
- Optimized for 60fps animations
- Memory efficient implementations
- Backward compatibility maintained

## 🔧 Animation Control
- All animations support comprehensive listeners
- Chain multiple animations seamlessly  
- Cancel and control animation states
- Customizable timing and interpolation


## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


