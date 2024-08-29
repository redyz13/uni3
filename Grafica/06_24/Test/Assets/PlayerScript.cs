using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerScript : MonoBehaviour {
    private Rigidbody rb;
    public float speed = 10.0f;

    void Start() {
        if (!TryGetComponent<Rigidbody>(out rb)) {
            rb = gameObject.AddComponent<Rigidbody>();
        }
    }

    void FixedUpdate() {
        Vector3 direction = new(Input.GetAxis("Horizontal"), 0, Input.GetAxis("Vertical"));
        Vector3 move = speed * Time.fixedDeltaTime * direction;
        rb.MovePosition(transform.position + move);
    }
}
