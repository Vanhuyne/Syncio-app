package online.syncio.component.message;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Provides an animation effect for a floating button using the Animator framework.
 * This class allows the button to smoothly show, hide, and stop animations.
 */
public class AnimationFloatingButton {

    private final Animator animator;
    private boolean show;

    /**
     * Initializes the AnimationFloatingButton with the specified layout and component.
     *
     * @param layout The layout where the component is placed.
     * @param com The component to be animated.
     */
    public AnimationFloatingButton(MigLayout layout, Component com) {
        this.animator = new Animator(300, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                int v;
                if (show) {
                    v = (int) (fraction * 50);
                } else {
                    v = (int) ((1f - fraction) * 50);
                }
                layout.setComponentConstraints(com, "pos 100%-50 100%-" + v + ",h 40,w 40");
                com.revalidate();
            }
        });
        this.animator.setResolution(1);
        this.animator.setAcceleration(.5f);
        this.animator.setDeceleration(.5f);
    }

    /**
     * Shows the floating button with a smooth animation effect.
     */
    public void show() {
        if (!show) {
            stop();
            show = true;
            animator.start();
        }
    }

    /**
     * Hides the floating button with a smooth animation effect.
     */
    public void hide() {
        if (show) {
            stop();
            show = false;
            animator.start();
        }
    }

    /**
     * Stops the ongoing animation.
     */
    public void stop() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }
    }

    /**
     * Checks if the animation is currently running.
     *
     * @return true if the animation is running, false otherwise.
     */
    public boolean isRunning() {
        return animator.isRunning();
    }
}
