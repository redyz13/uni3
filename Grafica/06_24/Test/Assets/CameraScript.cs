using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour {
    public Transform player;
    public float distance = 5.0f;
    public float height = 1.5f;
    public float sensitivity = 2.0f;

    private float yaw = 0.0f;
    private float pitch = 0.0f;

    void Start() {
        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;
    }

    void LateUpdate() {
        // Ottieni l'input del mouse
        yaw += Input.GetAxis("Mouse X") * sensitivity;
        pitch -= Input.GetAxis("Mouse Y") * sensitivity;

        // Limita l'angolo di inclinazione
        pitch = Mathf.Clamp(pitch, -30, 60);

        // Calcola la rotazione della camera
        Quaternion rotation = Quaternion.Euler(pitch, yaw, 0);

        // Calcola la posizione desiderata della camera
        Vector3 targetPosition = player.position - rotation * Vector3.forward * distance + Vector3.up * height;
        transform.position = targetPosition;

        // Fai guardare la camera verso il player
        transform.LookAt(player.position + Vector3.up * height);
    }
}
