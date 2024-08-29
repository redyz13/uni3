using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LightScript : MonoBehaviour {
    private new Light light;

    void Start() {
        light = GetComponent<Light>();
        light.color = Color.blue;
        light.intensity = 2.0f;
    }

    void Update() {
        
    }
}
