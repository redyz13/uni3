using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour {
    private Rigidbody rb;
    public float speed = 100.0f;
    public float jumpForce = 500.0f;

    void Start() {
        if (!TryGetComponent<Rigidbody>(out rb)) {
            rb = gameObject.AddComponent<Rigidbody>();
        }
    }

    void FixedUpdate() {
        Vector3 direction = new(Input.GetAxis("Horizontal"), 0, Input.GetAxis("Vertical"));
        Vector3 force = speed * Time.fixedDeltaTime * direction;
        rb.AddForce(force);

        if (Input.GetKeyDown(KeyCode.Space)) {
            rb.AddForce(jumpForce * Time.fixedDeltaTime * Vector3.up, ForceMode.Impulse);
        }
    }
}
