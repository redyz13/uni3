using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class GameLogic : MonoBehaviour {
    public GameObject walls;
    private GameObject[] wallsList; 
    private float colorChangeSpeed = 0.5f;

    void Start() {
        int childCount = walls.transform.childCount;
        wallsList = new GameObject[childCount];
        for (int i = 0; i < childCount; i++) {
            wallsList[i] = walls.transform.GetChild(i).gameObject;
        }
    }

    // Wall 1 behaviour: Change color on collision
    void OnCollisionEnter(Collision collision) {
        if (collision.gameObject == wallsList[0]) {
            GetComponent<Renderer>().material.color = new 
                Color(Random.Range(0f, 1f), Random.Range(0f, 1f), Random.Range(0f, 1f));
        }
    }

    // Wall 2 behaviour: Change color on collision stay
    void OnCollisionStay(Collision collision) {
        if (collision.gameObject == wallsList[1]) {
            Renderer wallRenderer = wallsList[1].GetComponent<Renderer>();
            Color currentColor = wallRenderer.material.color;
            wallRenderer.material.color = Color.Lerp(currentColor, Color.white, colorChangeSpeed * Time.deltaTime);
        }
    }

    // Wall 3 behaviour: Change color on trigger event
    void OnTriggerEnter(Collider other) {
        if (other.gameObject == wallsList[2]) {
            Renderer wall1Renderer = wallsList[0].GetComponent<Renderer>();
            if (wall1Renderer != null)
                wall1Renderer.material.color = new 
                    Color(Random.Range(0f, 1f), Random.Range(0f, 1f), Random.Range(0f, 1f));
        }
    }

    // Wall 4 behaviour: Destroy on collision exit
    void OnCollisionExit(Collision collision) {
        if (collision.gameObject == wallsList[3]) {
            Destroy(wallsList[3]);
        }
    }
}
